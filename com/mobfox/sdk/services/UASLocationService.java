package com.mobfox.sdk.services;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class UASLocationService {
    private LocationManager locationManager;
    private Context mContext;
    private String provider;

    public UASLocationService(Context context) {
        this.mContext = context;
        initManager();
    }

    public Location getLast() {
        Location l = null;
        try {
            if (VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0)) {
                if (this.locationManager == null || this.provider == null) {
                    initManager();
                }
                if (this.locationManager != null) {
                    this.provider = this.locationManager.getBestProvider(new Criteria(), true);
                    l = this.locationManager.getLastKnownLocation(this.provider);
                }
            }
            return l;
        } catch (Exception ex) {
            Log.e("UAM Loc Error", "Error getting location " + ex.getMessage());
            return null;
        }
    }

    private void initManager() {
        try {
            if (VERSION.SDK_INT < 23) {
                return;
            }
            if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                this.locationManager = (LocationManager) this.mContext.getSystemService("location");
            }
        } catch (Exception ex) {
            Log.e("UAM Loc Error", "Error on init location service: " + ex.getMessage());
        }
    }
}
