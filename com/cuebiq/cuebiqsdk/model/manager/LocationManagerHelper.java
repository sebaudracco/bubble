package com.cuebiq.cuebiqsdk.model.manager;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.utils.Utils;

public class LocationManagerHelper implements LocationListener {
    private OnLocationListener mListener;
    private Handler mLocationHandler = new Handler(Looper.myLooper());
    private LocationManager mLocationManager;

    class C17011 implements Runnable {
        C17011() {
        }

        public void run() {
            LocationManagerHelper.this.onLocation(null);
        }
    }

    public interface OnLocationListener {
        void onLocation(Location location);
    }

    private void onLocation(Location location) {
        if (this.mLocationManager != null) {
            this.mLocationManager.removeUpdates(this);
        }
        if (this.mLocationHandler != null) {
            this.mLocationHandler.removeCallbacksAndMessages(null);
            this.mLocationHandler = null;
        }
        if (this.mListener != null) {
            this.mListener.onLocation(location);
        }
    }

    public void getLocation(Context context, OnLocationListener onLocationListener) {
        this.mListener = onLocationListener;
        if (VERSION.SDK_INT <= 22 || context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            this.mLocationHandler.postDelayed(new C17011(), 20000);
            this.mLocationManager = (LocationManager) context.getSystemService("location");
            try {
                if (Utils.isLocationEnabled(context)) {
                    this.mLocationManager.requestSingleUpdate(CuebiqSDKImpl.getBeAudienceConfiguration(context).getAcc(), this, Looper.getMainLooper());
                    return;
                } else {
                    onLocation(this.mLocationManager.getLastKnownLocation(CuebiqSDKImpl.getBeAudienceConfiguration(context).getAcc()));
                    return;
                }
            } catch (Exception e) {
                onLocation(null);
                return;
            }
        }
        CuebiqSDKImpl.log("LocationManager -> Permission about LOCATION is not granted. Unable to acquire location data.");
        onLocation(null);
    }

    public void onLocationChanged(Location location) {
        onLocation(location);
    }

    public void onProviderDisabled(String str) {
        CuebiqSDKImpl.log("LocationManager -> onProviderDisabled");
    }

    public void onProviderEnabled(String str) {
        CuebiqSDKImpl.log("LocationManager -> onProviderEnabled");
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        CuebiqSDKImpl.log("LocationManager -> onStatusChanged");
    }
}
