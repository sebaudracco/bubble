package com.appsgeyser.sdk.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import java.util.List;

public class Geolocation {
    public static double[] getCoords(@NonNull Context context) {
        Location location = getLocation(context);
        double[] gps = new double[2];
        if (location != null) {
            gps[0] = location.getLatitude();
            gps[1] = location.getLongitude();
        }
        return gps;
    }

    @Nullable
    private static Location getLocation(@NonNull Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            List<String> providers = locationManager.getProviders(true);
            if (!(ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0)) {
                int i = providers.size() - 1;
                while (i >= 0 && locationManager.getLastKnownLocation((String) providers.get(i)) == null) {
                    i--;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
}
