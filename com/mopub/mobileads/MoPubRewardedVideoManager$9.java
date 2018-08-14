package com.mopub.mobileads;

import android.support.annotation.NonNull;

class MoPubRewardedVideoManager$9 extends MoPubRewardedVideoManager$ForEachMoPubIdRunnable {
    MoPubRewardedVideoManager$9(Class customEventClass, String thirdPartyId) {
        super(customEventClass, thirdPartyId);
    }

    protected void forEach(@NonNull String moPubId) {
        MoPubRewardedVideoManager.access$800(moPubId);
    }
}
