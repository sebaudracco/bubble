package com.mopub.mobileads;

class MoPubRewardedVideoManager$8 implements Runnable {
    final /* synthetic */ String val$currentlyShowingAdUnitId;
    final /* synthetic */ MoPubErrorCode val$errorCode;

    MoPubRewardedVideoManager$8(String str, MoPubErrorCode moPubErrorCode) {
        this.val$currentlyShowingAdUnitId = str;
        this.val$errorCode = moPubErrorCode;
    }

    public void run() {
        MoPubRewardedVideoManager.access$700(this.val$currentlyShowingAdUnitId, this.val$errorCode);
    }
}
