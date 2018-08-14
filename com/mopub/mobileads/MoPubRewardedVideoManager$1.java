package com.mopub.mobileads;

class MoPubRewardedVideoManager$1 implements Runnable {
    final /* synthetic */ String val$adUnitId;

    MoPubRewardedVideoManager$1(String str) {
        this.val$adUnitId = str;
    }

    public void run() {
        if (MoPubRewardedVideoManager.access$300(MoPubRewardedVideoManager.access$200()) != null) {
            MoPubRewardedVideoManager.access$300(MoPubRewardedVideoManager.access$200()).onRewardedVideoLoadSuccess(this.val$adUnitId);
        }
    }
}
