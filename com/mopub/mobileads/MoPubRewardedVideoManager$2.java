package com.mopub.mobileads;

import com.mopub.common.logging.MoPubLog;

class MoPubRewardedVideoManager$2 implements Runnable {
    final /* synthetic */ MoPubRewardedVideoManager this$0;
    final /* synthetic */ CustomEventRewardedAd val$customEvent;

    MoPubRewardedVideoManager$2(MoPubRewardedVideoManager this$0, CustomEventRewardedAd customEventRewardedAd) {
        this.this$0 = this$0;
        this.val$customEvent = customEventRewardedAd;
    }

    public void run() {
        MoPubLog.m12061d("Custom Event failed to load rewarded ad in a timely fashion.");
        MoPubRewardedVideoManager.onRewardedVideoLoadFailure(this.val$customEvent.getClass(), this.val$customEvent.getAdNetworkId(), MoPubErrorCode.NETWORK_TIMEOUT);
        this.val$customEvent.onInvalidate();
    }
}
