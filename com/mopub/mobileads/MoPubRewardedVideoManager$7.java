package com.mopub.mobileads;

import android.support.annotation.NonNull;

class MoPubRewardedVideoManager$7 extends MoPubRewardedVideoManager$ForEachMoPubIdRunnable {
    final /* synthetic */ MoPubErrorCode val$errorCode;

    MoPubRewardedVideoManager$7(Class customEventClass, String thirdPartyId, MoPubErrorCode moPubErrorCode) {
        this.val$errorCode = moPubErrorCode;
        super(customEventClass, thirdPartyId);
    }

    protected void forEach(@NonNull String moPubId) {
        MoPubRewardedVideoManager.access$700(moPubId, this.val$errorCode);
    }
}
