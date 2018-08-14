package com.mopub.mobileads;

import com.mopub.common.logging.MoPubLog;

class MoPubInterstitial$1 implements Runnable {
    final /* synthetic */ MoPubInterstitial this$0;

    MoPubInterstitial$1(MoPubInterstitial this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        MoPubLog.m12061d("Expiring unused Interstitial ad.");
        this.this$0.attemptStateTransition(MoPubInterstitial$InterstitialState.IDLE, true);
        if (!MoPubInterstitial$InterstitialState.SHOWING.equals(MoPubInterstitial.access$000(this.this$0)) && !MoPubInterstitial$InterstitialState.DESTROYED.equals(MoPubInterstitial.access$000(this.this$0))) {
            MoPubInterstitial.access$100(this.this$0).adFailed(MoPubErrorCode.EXPIRED);
        }
    }
}
