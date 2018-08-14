package com.mopub.mobileads;

class MoPubRewardedVideoManager$6 implements Runnable {
    final /* synthetic */ String val$currentlyShowingAdUnitId;

    MoPubRewardedVideoManager$6(String str) {
        this.val$currentlyShowingAdUnitId = str;
    }

    public void run() {
        MoPubRewardedVideoManager.access$600(this.val$currentlyShowingAdUnitId);
    }
}
