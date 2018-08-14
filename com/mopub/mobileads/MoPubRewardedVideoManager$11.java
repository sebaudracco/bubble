package com.mopub.mobileads;

import android.support.annotation.NonNull;

class MoPubRewardedVideoManager$11 extends MoPubRewardedVideoManager$ForEachMoPubIdRunnable {
    MoPubRewardedVideoManager$11(Class customEventClass, String thirdPartyId) {
        super(customEventClass, thirdPartyId);
    }

    protected void forEach(@NonNull String moPubId) {
        MoPubRewardedVideoManager.access$900(moPubId);
    }
}
