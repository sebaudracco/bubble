package com.mopub.mobileads;

import android.support.annotation.NonNull;

class MoPubRewardedVideoManager$4 extends MoPubRewardedVideoManager$ForEachMoPubIdRunnable {
    final /* synthetic */ MoPubErrorCode val$errorCode;

    MoPubRewardedVideoManager$4(Class customEventClass, String thirdPartyId, MoPubErrorCode moPubErrorCode) {
        this.val$errorCode = moPubErrorCode;
        super(customEventClass, thirdPartyId);
    }

    protected void forEach(@NonNull String moPubId) {
        MoPubRewardedVideoManager.access$400(MoPubRewardedVideoManager.access$200(), moPubId);
        MoPubRewardedVideoManager.access$500(MoPubRewardedVideoManager.access$200(), moPubId, this.val$errorCode);
    }
}
