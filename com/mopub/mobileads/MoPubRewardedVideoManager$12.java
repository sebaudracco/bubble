package com.mopub.mobileads;

class MoPubRewardedVideoManager$12 implements Runnable {
    final /* synthetic */ String val$currentlyShowingAdUnitId;

    MoPubRewardedVideoManager$12(String str) {
        this.val$currentlyShowingAdUnitId = str;
    }

    public void run() {
        MoPubRewardedVideoManager.access$900(this.val$currentlyShowingAdUnitId);
    }
}
