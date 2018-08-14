package com.appnext.base.p023b;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.appnext.base.C1061b;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

public class C1055j {
    protected static final String TAG = C1055j.class.getSimpleName();
    private static final long ks = 1000;
    private static final long kt = 500;
    private GoogleApiClient jd;
    private C1052a ku;
    private LocationSettingsRequest kv;
    private C1053b kw;
    private boolean kx;
    private C1054c ky;

    class C10491 implements ResultCallback<LocationSettingsResult> {
        final /* synthetic */ C1055j kz;

        C10491(C1055j c1055j) {
            this.kz = c1055j;
        }

        public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
            synchronized (this.kz) {
                this.kz.m2154a(locationSettingsResult.getStatus());
            }
        }
    }

    class C10502 implements ResultCallback<Status> {
        final /* synthetic */ C1055j kz;

        C10502(C1055j c1055j) {
            this.kz = c1055j;
        }

        public void onResult(@NonNull Status status) {
            synchronized (this.kz) {
                this.kz.m2157b(status);
            }
        }
    }

    class C10513 implements ResultCallback<Status> {
        final /* synthetic */ C1055j kz;

        C10513(C1055j c1055j) {
            this.kz = c1055j;
        }

        public void onResult(@NonNull Status status) {
            synchronized (this.kz) {
                try {
                    if (this.kz.jd != null && this.kz.jd.isConnected()) {
                        this.kz.jd.disconnect();
                    }
                    this.kz.jd = null;
                    this.kz.ku = null;
                    this.kz.kw = null;
                } catch (Throwable th) {
                    C1061b.m2191a(th);
                }
            }
        }
    }

    private class C1052a implements ConnectionCallbacks, OnConnectionFailedListener {
        final /* synthetic */ C1055j kz;

        private C1052a(C1055j c1055j) {
            this.kz = c1055j;
        }

        @SuppressLint({"all"})
        public void onConnected(Bundle bundle) {
            synchronized (this.kz) {
                this.kz.cE();
            }
        }

        public void onConnectionSuspended(int i) {
            synchronized (this.kz) {
                this.kz.cG();
                this.kz.aE("Connection suspended");
            }
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            C1061b.m2190W("Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
            synchronized (this.kz) {
                this.kz.cG();
                this.kz.aE(connectionResult.getErrorMessage());
            }
        }
    }

    private class C1053b implements LocationListener {
        final /* synthetic */ C1055j kz;

        private C1053b(C1055j c1055j) {
            this.kz = c1055j;
        }

        public void onLocationChanged(Location location) {
            synchronized (this.kz) {
                this.kz.cG();
                this.kz.m2159e(location);
            }
        }
    }

    public interface C1054c {
        void mo2039a(Location location);

        void onError(String str);
    }

    public void m2160a(C1054c c1054c) {
        this.ky = c1054c;
    }

    public void init() {
        synchronized (this) {
            if (C1055j.hasPermission()) {
                this.kx = false;
                cB();
                return;
            }
            aE("no location permissions");
        }
    }

    public void cz() {
        synchronized (this) {
            this.kx = true;
            if (this.kw != null) {
                cG();
            }
        }
    }

    public static Location cA() {
        try {
            if (!C1055j.hasPermission()) {
                return null;
            }
            LocationManager locationManager = (LocationManager) C1043d.getContext().getSystemService("location");
            Location location = null;
            for (String lastKnownLocation : locationManager.getProviders(true)) {
                Location lastKnownLocation2 = locationManager.getLastKnownLocation(lastKnownLocation);
                if (lastKnownLocation2 != null) {
                    if (location != null && lastKnownLocation2.getAccuracy() >= location.getAccuracy()) {
                        lastKnownLocation2 = location;
                    }
                    location = lastKnownLocation2;
                }
            }
            return location;
        } catch (Throwable th) {
            return null;
        }
    }

    private void cB() {
        if (cl()) {
            this.jd.connect();
        } else {
            aE(TAG + "  Google Api LocationServices not available");
        }
    }

    private boolean cl() {
        try {
            this.ku = new C1052a();
            this.jd = new Builder(C1043d.getContext()).addConnectionCallbacks(this.ku).addOnConnectionFailedListener(this.ku).addApi(LocationServices.API).build();
            return true;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return false;
        }
    }

    private static LocationRequest cC() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(ks);
        locationRequest.setFastestInterval(kt);
        locationRequest.setPriority(100);
        return locationRequest;
    }

    private void cD() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(C1055j.cC());
        this.kv = builder.build();
    }

    private void cE() {
        if (this.kx) {
            cG();
            return;
        }
        try {
            cD();
            LocationServices.SettingsApi.checkLocationSettings(this.jd, this.kv).setResultCallback(new C10491(this));
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    private void m2154a(Status status) {
        if (this.kx) {
            cG();
            return;
        }
        switch (status.getStatusCode()) {
            case 0:
                cF();
                return;
            default:
                cG();
                aE(status.getStatusMessage());
                return;
        }
    }

    @SuppressLint({"all"})
    private void cF() {
        if (this.kx) {
            cG();
            return;
        }
        try {
            this.kw = new C1053b();
            LocationServices.FusedLocationApi.requestLocationUpdates(this.jd, C1055j.cC(), this.kw).setResultCallback(new C10502(this));
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    private void m2157b(Status status) {
        if (this.kx) {
            cG();
        } else if (!status.isSuccess()) {
            cG();
            aE(status.getStatusMessage());
        }
    }

    private void cG() {
        try {
            if (this.kw != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(this.jd, this.kw).setResultCallback(new C10513(this));
            } else if (this.jd != null) {
                this.jd.disconnect();
                this.jd = null;
                this.ku = null;
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    private static boolean hasPermission() {
        return C1045f.m2133g(C1043d.getContext(), "android.permission.ACCESS_FINE_LOCATION") || C1045f.m2133g(C1043d.getContext(), "android.permission.ACCESS_COARSE_LOCATION");
    }

    private void m2159e(Location location) {
        if (this.ky != null) {
            this.ky.mo2039a(location);
        }
    }

    private void aE(String str) {
        if (this.ky != null) {
            this.ky.onError(str);
        }
    }
}
