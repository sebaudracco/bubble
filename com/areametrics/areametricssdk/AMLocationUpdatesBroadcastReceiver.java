package com.areametrics.areametricssdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import com.google.android.gms.location.LocationResult;
import java.util.List;

public class AMLocationUpdatesBroadcastReceiver extends BroadcastReceiver {
    private static final String f1823a = ("AMS-" + AMLocationUpdatesBroadcastReceiver.class.getSimpleName());

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if ("com.areametrics.areametricssdk.AMLocationUpdatesBroadcastReceiver.PROCESS_UPDATES".equals(intent.getAction())) {
                LocationResult extractResult = LocationResult.extractResult(intent);
                if (extractResult != null) {
                    List<Location> locations = extractResult.getLocations();
                    C1367d networkController = AreaMetricsSDK.INSTANCE.getNetworkController();
                    if (networkController != null) {
                        for (Location location : locations) {
                            if (location != null) {
                                long j = networkController.m2528b().m2629n().getSharedPreferences("AMS_BGLOC_EXPIRY", 0).getLong("bgloc_expiry", 0);
                                long currentTimeMillis = System.currentTimeMillis();
                                if (j <= currentTimeMillis) {
                                    int i = networkController.m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("bgloc_time_filter", "release".equals("debug") ? 0 : 43200);
                                    if (i > 0) {
                                        C1378g b = networkController.m2528b();
                                        j = (((long) i) * 1000) + currentTimeMillis;
                                        if (Math.abs(System.currentTimeMillis() - j) < 86400000) {
                                            Editor edit = b.m2629n().getSharedPreferences("AMS_BGLOC_EXPIRY", 0).edit();
                                            edit.putLong("bgloc_expiry", j);
                                            edit.apply();
                                        }
                                    }
                                    networkController.m2523a(location, "lc");
                                }
                            }
                        }
                    }
                }
            }
        }
        C1372e beaconTracker = AreaMetricsSDK.INSTANCE.getBeaconTracker();
        if (beaconTracker != null) {
            beaconTracker.m2557a();
        }
    }
}
