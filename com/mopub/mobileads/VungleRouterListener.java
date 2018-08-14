package com.mopub.mobileads;

import android.support.annotation.NonNull;

public interface VungleRouterListener {
    void onAdAvailabilityUpdate(@NonNull String str, boolean z);

    void onAdEnd(@NonNull String str, boolean z, boolean z2);

    void onAdStart(@NonNull String str);

    void onUnableToPlayAd(@NonNull String str, String str2);
}
