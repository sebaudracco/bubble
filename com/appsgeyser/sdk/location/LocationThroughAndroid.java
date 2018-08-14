package com.appsgeyser.sdk.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

public class LocationThroughAndroid {
    private static volatile Location lastKnownLocation;
    private static LocationListener locationListener = new C12961();

    static class C12961 implements LocationListener {
        C12961() {
        }

        public void onLocationChanged(Location location) {
            LocationThroughAndroid.lastKnownLocation = location;
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    }

    public static void startLocationUpdates(Context context) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (locationManager != null) {
                locationManager.requestLocationUpdates("network", 60000, 100.0f, locationListener);
                locationManager.requestLocationUpdates("gps", 60000, 100.0f, locationListener);
            }
        }
    }

    public static void stopLocationUpdates(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    public static Location getLastKnownLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return lastKnownLocation;
        }
        if (locationManager == null) {
            return lastKnownLocation;
        }
        Location lastKnownLocationGPS = locationManager.getLastKnownLocation("gps");
        Location lastKnownLocationNETWORK = locationManager.getLastKnownLocation("network");
        if (lastKnownLocationNETWORK == null) {
            return lastKnownLocationGPS != null ? lastKnownLocationGPS : lastKnownLocation;
        } else {
            return lastKnownLocationNETWORK;
        }
    }
}
