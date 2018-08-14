package com.mobfox.sdk.background;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.mobfox.sdk.constants.Constants;

public class GetLocation extends AsyncTask<Void, Void, Void> implements LocationListener {
    final int MIN_DIST = 10;
    final int MIN_TIME = 50;
    Context context;
    Listener listener;
    LocationManager locationManager;
    Handler mainHandler;
    GetLocation self;

    class C35401 implements Runnable {
        C35401() {
        }

        public void run() {
            if (ContextCompat.checkSelfPermission(GetLocation.this.context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                GetLocation.this.locationManager.requestLocationUpdates("gps", 50, Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT, GetLocation.this.self);
            }
        }
    }

    public interface Listener {
        void onLocation(Location location);
    }

    public GetLocation(Listener listener, Context context) {
        this.listener = listener;
        this.context = context;
        this.self = this;
        this.mainHandler = new Handler(context.getMainLooper());
    }

    public void onLocationChanged(Location location) {
        this.listener.onLocation(location);
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            this.locationManager.removeUpdates(this.self);
        }
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }

    protected Void doInBackground(Void... params) {
        this.locationManager = (LocationManager) this.context.getSystemService("location");
        if (this.locationManager == null) {
            Log.d(Constants.MOBFOX_BANNER, "location manager not available");
        } else {
            Location location = this.locationManager.getLastKnownLocation("gps");
            if (location != null) {
                this.listener.onLocation(location);
            } else if (this.locationManager.isProviderEnabled("gps")) {
                this.mainHandler.post(new C35401());
            } else {
                Log.d(Constants.MOBFOX_BANNER, "gps not enabled");
            }
        }
        return null;
    }
}
