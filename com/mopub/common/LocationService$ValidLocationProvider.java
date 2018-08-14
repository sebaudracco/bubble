package com.mopub.common;

import android.content.Context;
import android.support.annotation.NonNull;
import com.mopub.common.util.DeviceUtils;

public enum LocationService$ValidLocationProvider {
    NETWORK("network"),
    GPS("gps");
    
    @NonNull
    final String name;

    private LocationService$ValidLocationProvider(@NonNull String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    private boolean hasRequiredPermissions(@NonNull Context context) {
        switch (this) {
            case NETWORK:
                if (DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") || DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                    return true;
                }
                return false;
            case GPS:
                return DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION");
            default:
                return false;
        }
    }
}
