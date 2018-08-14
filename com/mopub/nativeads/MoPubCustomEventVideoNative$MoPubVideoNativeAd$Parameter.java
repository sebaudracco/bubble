package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import java.util.HashSet;
import java.util.Set;

enum MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter {
    IMPRESSION_TRACKER("imptracker", true),
    CLICK_TRACKER("clktracker", true),
    TITLE("title", false),
    TEXT("text", false),
    IMAGE_URL("mainimage", false),
    ICON_URL("iconimage", false),
    CLICK_DESTINATION("clk", false),
    FALLBACK("fallback", false),
    CALL_TO_ACTION("ctatext", false),
    VAST_VIDEO("video", false);
    
    @NonNull
    @VisibleForTesting
    static final Set<String> requiredKeys = null;
    @NonNull
    final String mName;
    final boolean mRequired;

    static {
        requiredKeys = new HashSet();
        MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter parameter = values[i];
            if (parameter.mRequired) {
                requiredKeys.add(parameter.mName);
            }
            i++;
        }
    }

    private MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter(@NonNull String name, boolean required) {
        Preconditions.checkNotNull(name);
        this.mName = name;
        this.mRequired = required;
    }

    @Nullable
    static MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter from(@NonNull String name) {
        Preconditions.checkNotNull(name);
        for (MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter parameter : values()) {
            if (parameter.mName.equals(name)) {
                return parameter;
            }
        }
        return null;
    }
}
