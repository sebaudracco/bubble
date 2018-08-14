package com.mopub.mobileads;

import com.mopub.common.logging.MoPubLog;

class FacebookInterstitial$1 implements Runnable {
    final /* synthetic */ FacebookInterstitial this$0;

    FacebookInterstitial$1(FacebookInterstitial this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        if (FacebookInterstitial.access$000(this.this$0) != null) {
            MoPubLog.m12061d("Expiring unused Facebook Interstitial ad due to Facebook's 60-minute expiration policy.");
            FacebookInterstitial.access$000(this.this$0).onInterstitialFailed(MoPubErrorCode.EXPIRED);
            this.this$0.onInvalidate();
        }
    }
}
