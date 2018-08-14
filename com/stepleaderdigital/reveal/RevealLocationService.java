package com.stepleaderdigital.reveal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import com.fyber.ads.videos.RewardedVideoActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.stats.CodePackage;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import com.stepleaderdigital.reveal.Reveal.GlobalLocation;
import com.stepleaderdigital.reveal.Reveal.LocationService;
import com.stepleaderdigital.reveal.Reveal.LocationService.OnValidLocationCallback;
import com.stepleaderdigital.reveal.Reveal.RevealLocation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RevealLocationService extends LocationCallback implements LocationService, ConnectionCallbacks, OnConnectionFailedListener {
    private Context applicationContext;
    private Location currentLocation;
    private GoogleApiClient googleApiClient;
    private Location lastLocationReported;
    private boolean locationDiscoveryActive = false;
    private LocationRequest locationRequest;
    private PendingResult<Status> locationResult;
    private double locationTime = 0.0d;
    public double locationValidTime = 60.0d;
    private ScheduledExecutorService noLocationTimerExecutionScheduler = Executors.newScheduledThreadPool(1);
    private OnValidLocationCallback onValidLocationCallback;
    private ArrayList<OnValidLocationCallback> pendingLocationCallbacks = new ArrayList();

    class C40251 implements OnValidLocationCallback {
        C40251() {
        }

        public void onLocationFound() {
            RevealLocationService.this.firePendingCallbacks();
        }
    }

    public Location getCurrentLocation(Context context) {
        return this.currentLocation;
    }

    public void startLocationMonitoring(Context context) {
        this.applicationContext = context;
        if (Reveal.selfPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") || Reveal.selfPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            Reveal.getInstance().setLocationSharingEnabled(Boolean.valueOf(true));
        }
        if (this.googleApiClient == null) {
            startGoogleApi(context);
        } else {
            internalStartLocationMonitoring(context);
        }
    }

    public void stopLocationMonitoring(Context context) {
        if (this.googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.googleApiClient, this);
        } else {
            Reveal.log("Attempting to stop location monitoring with no Google API available enabled=" + Reveal.getInstance().getLocationSharingEnabled().toString(), "WARNING", CodePackage.LOCATION);
            if (this.locationRequest != null) {
                Reveal.log("       locationRequest: " + this.locationRequest.toString(), "WARNING", CodePackage.LOCATION);
            }
        }
        this.locationRequest = null;
        this.googleApiClient = null;
        Reveal.getInstance().setLocationSharingEnabled(Boolean.valueOf(false));
        Reveal.log("Stop monitoring location", "STATE");
        this.locationDiscoveryActive = false;
    }

    public void waitForValidLocation(OnValidLocationCallback callback) {
        if (isLocationCurrent()) {
            Reveal.log("We have a current location so no need to wait", "STATE");
            if (callback != null) {
                callback.onLocationFound();
            }
        } else if (callback != null) {
            Reveal.log("We need to request a new location", "STATE");
            synchronized (this) {
                this.pendingLocationCallbacks.add(callback);
            }
            timeOut(this.locationValidTime, new C40251());
        }
    }

    private boolean isLocationCurrent() {
        if ((((double) System.currentTimeMillis()) / 1000.0d) - this.locationTime < this.locationValidTime) {
            return true;
        }
        return false;
    }

    private synchronized void internalStartLocationMonitoring(Context context) {
        this.locationRequest = new LocationRequest();
        this.locationRequest.setInterval(300000);
        this.locationRequest.setFastestInterval(60000);
        this.locationRequest.setSmallestDisplacement(100.0f);
        this.locationRequest.setPriority(104);
        if (this.googleApiClient.isConnected()) {
            try {
                if (Reveal.selfPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") || Reveal.selfPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                    this.locationResult = LocationServices.FusedLocationApi.requestLocationUpdates(this.googleApiClient, this.locationRequest, this, Looper.getMainLooper());
                    Reveal.log("Start monitoring location", "STATE");
                }
            } catch (IllegalStateException e) {
                Reveal.log("Google API connection has not been established yet, locations will not be provided until a connection has been established", "WARNING", "STATE");
            } catch (SecurityException e2) {
                Reveal.log("Old android permissions scheme used to setup permissions and not handling shutoff of permissions properly in app.. stopping location monitoring", "WARNING", "STATE");
                stopLocationMonitoring(context);
            }
        }
    }

    private synchronized void startGoogleApi(Context context) {
        Reveal.log("Starting Google location services", "STATE");
        this.googleApiClient = new GoogleApiClient$Builder(context).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        this.googleApiClient.connect();
    }

    private void firePendingCallbacks() {
        ArrayList<OnValidLocationCallback> callbacks;
        synchronized (this) {
            callbacks = this.pendingLocationCallbacks;
            this.pendingLocationCallbacks = new ArrayList();
        }
        if (!isLocationCurrent() && callbacks.size() > 0) {
            Reveal.log("Waiting for location timed out without a valid location", "STATE");
        }
        Iterator it = callbacks.iterator();
        while (it.hasNext()) {
            OnValidLocationCallback callback = (OnValidLocationCallback) it.next();
            if (callback != null) {
                callback.onLocationFound();
            }
        }
        Boolean needUpdate = Boolean.valueOf(true);
        if (!(this.currentLocation == null || this.lastLocationReported == null || ((double) this.currentLocation.distanceTo(this.lastLocationReported)) >= 100.0d)) {
            needUpdate = Boolean.valueOf(false);
        }
        if (needUpdate.booleanValue()) {
            this.lastLocationReported = this.currentLocation;
            if (this.onValidLocationCallback != null) {
                this.onValidLocationCallback.onLocationFound();
            }
        }
    }

    public void onConnected(Bundle connectionHint) {
        if (this.applicationContext == null) {
            return;
        }
        if (Reveal.selfPermissionGranted(this.applicationContext, "android.permission.ACCESS_FINE_LOCATION") || Reveal.selfPermissionGranted(this.applicationContext, "android.permission.ACCESS_COARSE_LOCATION")) {
            Reveal.log("Connected to google location API, locations will now be provided", "STATE");
            Reveal.getInstance().setLocationSharingEnabled(Boolean.valueOf(true));
            Reveal.getInstance().setStatus("location", 1, "Connected to google location API");
            try {
                Location location = LocationServices.FusedLocationApi.getLastLocation(this.googleApiClient);
                if (location != null) {
                    this.currentLocation = location;
                    onLocationChanged(location);
                }
                internalStartLocationMonitoring(this.applicationContext);
            } catch (SecurityException securityException) {
                Reveal.log("Old android permissions scheme used to setup permissions and not handling shutoff of permissions properly in app.. stopping location monitoring", RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "STATE");
                Reveal.getInstance().setStatus("location", 0, "Security: " + securityException);
                stopLocationMonitoring(this.applicationContext);
            }
        }
    }

    public void onLocationAvailability(LocationAvailability locationAvailability) {
        super.onLocationAvailability(locationAvailability);
        Reveal.log("onLocationAvailability( " + locationAvailability + " ) called", "DEBUG", "STATE");
        this.locationDiscoveryActive = locationAvailability.isLocationAvailable();
        if (this.locationDiscoveryActive) {
            Reveal.getInstance().setStatus("location", 1, "Location available");
        } else {
            Reveal.getInstance().setStatus("location", 0, "No location available");
        }
    }

    public void onLocationResult(LocationResult locationResult) {
        super.onLocationResult(locationResult);
        Reveal.log("onLocationResult( " + locationResult + " ) called", "DEBUG", "STATE");
        onLocationChanged(locationResult.getLastLocation());
    }

    public void onConnectionSuspended(int cause) {
        Reveal.getInstance().setLocationSharingEnabled(Boolean.valueOf(false));
        Reveal.log("Google API suspended with cause: " + cause, "WARNING", "STATE");
        Reveal.getInstance().setStatus("location", 0, "Google API suspended with cause: " + cause);
    }

    public void onConnectionFailed(ConnectionResult result) {
        Reveal.log("Google API failed with result: " + result.toString(), RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "STATE");
        Reveal.getInstance().setStatus("location", 0, "Google API failed: " + result.toString());
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            double now = ((double) System.currentTimeMillis()) / 1000.0d;
            this.currentLocation = location;
            if (this.lastLocationReported == null) {
                this.lastLocationReported = location;
            }
            if (((double) location.distanceTo(this.lastLocationReported)) >= 100.0d || this.locationTime < MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) {
                Reveal.log("Google gave us a new location:" + location, "STATE");
                RevealLocation loc = new RevealLocation();
                loc.setLocation(new GlobalLocation(location));
                this.lastLocationReported = location;
                Reveal.getInstance().recordEvent("Location Updated");
                Reveal.getInstance().addEvent(Reveal.getInstance().getApplicationContext(), loc);
                Reveal.getInstance().setStatus("location", 1, "Got location: " + location);
            } else {
                Reveal.getInstance().setStatus("location", 1, "Got location: " + location + " (similar)");
            }
            this.locationTime = now;
            firePendingCallbacks();
        }
    }

    private void timeOut(final double seconds, final OnValidLocationCallback callback) {
        this.noLocationTimerExecutionScheduler.schedule(new Runnable() {
            public void run() {
                synchronized (this) {
                    boolean updated = true;
                }
                if (RevealLocationService.this.currentLocation != null && 1 == null) {
                    long diff = (long) ((((double) System.currentTimeMillis()) - ((double) RevealLocationService.this.currentLocation.getTime())) / 1000.0d);
                    if (((double) diff) <= seconds) {
                        updated = true;
                    } else {
                        Reveal.getInstance().setStatus("location", 2, "Waiting for valid location timed out, current age " + diff + " seconds");
                    }
                }
                if (!updated) {
                    Reveal.log("Waiting for valid location timed out", "OPT");
                }
                if (callback != null) {
                    callback.onLocationFound();
                }
            }
        }, (long) seconds, TimeUnit.SECONDS);
    }

    public double getLocationValidTime() {
        return this.locationValidTime;
    }

    public void setLocationValidTime(double locationValidTime) {
        this.locationValidTime = locationValidTime;
    }

    public OnValidLocationCallback getLocationUpdated() {
        return this.onValidLocationCallback;
    }

    public void setOnValidLocationCallback(OnValidLocationCallback onValidLocationCallback) {
        this.onValidLocationCallback = onValidLocationCallback;
    }

    public boolean isLocationDiscoveryActive() {
        return this.locationDiscoveryActive;
    }

    public void setLocationDiscoveryActive(boolean locationDiscoveryActive) {
        this.locationDiscoveryActive = locationDiscoveryActive;
    }
}
