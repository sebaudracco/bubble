package com.mopub.mobileads;

import android.support.annotation.NonNull;

class MoPubRewardedVideoManager$5 extends MoPubRewardedVideoManager$ForEachMoPubIdRunnable {
    MoPubRewardedVideoManager$5(Class customEventClass, String thirdPartyId) {
        super(customEventClass, thirdPartyId);
    }

    protected void forEach(@NonNull String moPubId) {
        MoPubRewardedVideoManager.access$600(moPubId);
    }
}
