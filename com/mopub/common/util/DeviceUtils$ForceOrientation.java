package com.mopub.common.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.appnext.core.Ad;

public enum DeviceUtils$ForceOrientation {
    FORCE_PORTRAIT(Ad.ORIENTATION_PORTRAIT),
    FORCE_LANDSCAPE(Ad.ORIENTATION_LANDSCAPE),
    DEVICE_ORIENTATION("device"),
    UNDEFINED("");
    
    @NonNull
    private final String mKey;

    private DeviceUtils$ForceOrientation(@NonNull String key) {
        this.mKey = key;
    }

    @NonNull
    public static DeviceUtils$ForceOrientation getForceOrientation(@Nullable String key) {
        for (DeviceUtils$ForceOrientation orientation : values()) {
            if (orientation.mKey.equalsIgnoreCase(key)) {
                return orientation;
            }
        }
        return UNDEFINED;
    }
}
