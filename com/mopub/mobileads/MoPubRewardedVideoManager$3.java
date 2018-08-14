package com.mopub.mobileads;

import android.support.annotation.NonNull;

class MoPubRewardedVideoManager$3 extends MoPubRewardedVideoManager$ForEachMoPubIdRunnable {
    MoPubRewardedVideoManager$3(Class customEventClass, String thirdPartyId) {
        super(customEventClass, thirdPartyId);
    }

    protected void forEach(@NonNull String moPubId) {
        MoPubRewardedVideoManager.access$400(MoPubRewardedVideoManager.access$200(), moPubId);
        if (MoPubRewardedVideoManager.access$300(MoPubRewardedVideoManager.access$200()) != null) {
            MoPubRewardedVideoManager.access$300(MoPubRewardedVideoManager.access$200()).onRewardedVideoLoadSuccess(moPubId);
        }
    }
}
