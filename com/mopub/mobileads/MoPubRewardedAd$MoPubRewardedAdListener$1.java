package com.mopub.mobileads;

import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubRewardedAd.MoPubRewardedAdListener;

class MoPubRewardedAd$MoPubRewardedAdListener$1 implements Runnable {
    final /* synthetic */ MoPubRewardedAdListener this$1;
    final /* synthetic */ MoPubRewardedAd val$this$0;

    MoPubRewardedAd$MoPubRewardedAdListener$1(MoPubRewardedAdListener this$1, MoPubRewardedAd moPubRewardedAd) {
        this.this$1 = this$1;
        this.val$this$0 = moPubRewardedAd;
    }

    public void run() {
        MoPubLog.m12061d("Expiring unused Rewarded ad.");
        this.this$1.onInterstitialFailed(MoPubErrorCode.EXPIRED);
    }
}
