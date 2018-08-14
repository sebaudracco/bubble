package com.onesignal;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.onesignal.OneSignal.LOG_LEVEL;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

class LocationGMS {
    private static final int TIME_BACKGROUND = 600;
    private static final int TIME_FOREGROUND = 300;
    private static Context classContext;
    private static Thread fallbackFailThread;
    private static boolean locationCoarse;
    private static LocationHandler locationHandler;
    private static LocationUpdateListener locationUpdateListener;
    private static GoogleApiClientCompatProxy mGoogleApiClient;
    static String requestPermission;

    static class C38891 implements Runnable {
        C38891() {
        }

        public void run() {
            try {
                Thread.sleep(30000);
                OneSignal.Log(LOG_LEVEL.WARN, "Location permission exists but GoogleApiClient timed out. Maybe related to mismatch google-play aar versions.");
                LocationGMS.fireFailedComplete();
            } catch (Throwable th) {
            }
        }
    }

    static class FusedLocationApiWrapper {
        FusedLocationApiWrapper() {
        }

        static PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
            return LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationListener);
        }

        static PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, LocationListener locationListener) {
            return LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, locationListener);
        }

        static Location getLastLocation(GoogleApiClient googleApiClient) {
            return LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        }
    }

    private static class GoogleApiClientListener implements ConnectionCallbacks, OnConnectionFailedListener {
        private GoogleApiClientListener() {
        }

        public void onConnected(Bundle bundle) {
            PermissionsActivity.answered = false;
            Location location = FusedLocationApiWrapper.getLastLocation(LocationGMS.mGoogleApiClient.realInstance());
            if (location != null) {
                LocationGMS.receivedLocationPoint(location);
                LocationGMS.mGoogleApiClient.disconnect();
                return;
            }
            LocationGMS.locationUpdateListener = new LocationUpdateListener(LocationGMS.mGoogleApiClient.realInstance());
        }

        public void onConnectionSuspended(int i) {
            LocationGMS.fireFailedComplete();
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            LocationGMS.fireFailedComplete();
        }
    }

    interface LocationHandler {
        void complete(LocationPoint locationPoint);
    }

    static class LocationPoint {
        Float accuracy;
        Boolean bg;
        Double lat;
        Double log;
        Long timeStamp;
        Integer type;

        LocationPoint() {
        }
    }

    private static class LocationUpdateListener implements LocationListener {
        private GoogleApiClient mGoogleApiClient;

        LocationUpdateListener(GoogleApiClient googleApiClient) {
            this.mGoogleApiClient = googleApiClient;
            FusedLocationApiWrapper.removeLocationUpdates(this.mGoogleApiClient, this);
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setMaxWaitTime(20000);
            locationRequest.setPriority(102);
            FusedLocationApiWrapper.requestLocationUpdates(this.mGoogleApiClient, locationRequest, this);
        }

        public void onLocationChanged(Location location) {
            LocationGMS.receivedLocationPoint(location);
            if (this.mGoogleApiClient.isConnected()) {
                FusedLocationApiWrapper.removeLocationUpdates(this.mGoogleApiClient, this);
                this.mGoogleApiClient.disconnect();
            }
        }
    }

    LocationGMS() {
    }

    static void scheduleUpdate(Context context) {
        if (hasLocationPermission(context) || !OneSignal.shareLocation) {
            SyncService.scheduleServiceSyncTask(context, getLastLocationTime(context) + ((long) ((OneSignal.isForeground() ? 300 : 600) * 1000)));
        }
    }

    private static void setLastLocationTime(long time) {
        Editor editor = OneSignal.getGcmPreferences(classContext).edit();
        editor.putLong("OS_LAST_LOCATION_TIME", time);
        editor.apply();
    }

    private static long getLastLocationTime(Context context) {
        return OneSignal.getGcmPreferences(context).getLong("OS_LAST_LOCATION_TIME", -600000);
    }

    private static boolean hasLocationPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    static void getLocation(Context context, boolean promptLocation, LocationHandler handler) {
        classContext = context;
        locationHandler = handler;
        if (OneSignal.shareLocation) {
            int locationCoarsePermission = -1;
            int locationFinePermission = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION");
            if (locationFinePermission == -1) {
                locationCoarsePermission = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION");
                locationCoarse = true;
            }
            if (VERSION.SDK_INT < 23) {
                if (locationFinePermission == 0 || locationCoarsePermission == 0) {
                    startGetLocation();
                    return;
                } else {
                    handler.complete(null);
                    return;
                }
            } else if (locationFinePermission != 0) {
                try {
                    List<String> permissionList = Arrays.asList(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions);
                    if (permissionList.contains("android.permission.ACCESS_FINE_LOCATION")) {
                        requestPermission = "android.permission.ACCESS_FINE_LOCATION";
                    } else if (permissionList.contains("android.permission.ACCESS_COARSE_LOCATION") && locationCoarsePermission != 0) {
                        requestPermission = "android.permission.ACCESS_COARSE_LOCATION";
                    }
                    if (requestPermission != null && promptLocation) {
                        PermissionsActivity.startPrompt();
                        return;
                    } else if (locationCoarsePermission == 0) {
                        startGetLocation();
                        return;
                    } else {
                        fireFailedComplete();
                        return;
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                    return;
                }
            } else {
                startGetLocation();
                return;
            }
        }
        fireFailedComplete();
    }

    static void startGetLocation() {
        if (fallbackFailThread == null) {
            try {
                startFallBackThread();
                GoogleApiClientListener googleApiClientListener = new GoogleApiClientListener();
                mGoogleApiClient = new GoogleApiClientCompatProxy(new GoogleApiClient$Builder(classContext).addApi(LocationServices.API).addConnectionCallbacks(googleApiClientListener).addOnConnectionFailedListener(googleApiClientListener).build());
                mGoogleApiClient.connect();
            } catch (Throwable t) {
                OneSignal.Log(LOG_LEVEL.WARN, "Location permission exists but there was an error initializing: ", t);
                fireFailedComplete();
            }
        }
    }

    private static void startFallBackThread() {
        fallbackFailThread = new Thread(new C38891(), "OS_GMS_LOCATION_FALLBACK");
        fallbackFailThread.start();
    }

    static void fireFailedComplete() {
        PermissionsActivity.answered = false;
        fireComplete(null);
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    private static void fireComplete(LocationPoint point) {
        locationHandler.complete(point);
        if (!(fallbackFailThread == null || Thread.currentThread().equals(fallbackFailThread))) {
            fallbackFailThread.interrupt();
        }
        fallbackFailThread = null;
    }

    private static void receivedLocationPoint(Location location) {
        int i = 0;
        LocationPoint point = new LocationPoint();
        point.accuracy = Float.valueOf(location.getAccuracy());
        point.bg = Boolean.valueOf(!OneSignal.isForeground());
        if (!locationCoarse) {
            i = 1;
        }
        point.type = Integer.valueOf(i);
        point.timeStamp = Long.valueOf(location.getTime());
        if (locationCoarse) {
            point.lat = Double.valueOf(new BigDecimal(location.getLatitude()).setScale(7, RoundingMode.HALF_UP).doubleValue());
            point.log = Double.valueOf(new BigDecimal(location.getLongitude()).setScale(7, RoundingMode.HALF_UP).doubleValue());
        } else {
            point.lat = Double.valueOf(location.getLatitude());
            point.log = Double.valueOf(location.getLongitude());
        }
        fireComplete(point);
        setLastLocationTime(System.currentTimeMillis());
        scheduleUpdate(classContext);
    }
}
