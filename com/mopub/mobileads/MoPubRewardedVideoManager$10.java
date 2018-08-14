package com.mopub.mobileads;

class MoPubRewardedVideoManager$10 implements Runnable {
    final /* synthetic */ String val$currentlyShowingAdUnitId;

    MoPubRewardedVideoManager$10(String str) {
        this.val$currentlyShowingAdUnitId = str;
    }

    public void run() {
        MoPubRewardedVideoManager.access$800(this.val$currentlyShowingAdUnitId);
    }
}
