package com.facebook.ads.internal.protocol;

import android.text.TextUtils;
import com.mopub.common.AdType;
import java.util.Locale;

public enum AdPlacementType {
    UNKNOWN("unknown"),
    BANNER("banner"),
    INTERSTITIAL("interstitial"),
    NATIVE("native"),
    INSTREAM("instream"),
    REWARDED_VIDEO(AdType.REWARDED_VIDEO);
    
    private String f4968a;

    private AdPlacementType(String str) {
        this.f4968a = str;
    }

    public static AdPlacementType fromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return UNKNOWN;
        }
        try {
            return valueOf(str.toUpperCase(Locale.US));
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

    public String toString() {
        return this.f4968a;
    }
}
