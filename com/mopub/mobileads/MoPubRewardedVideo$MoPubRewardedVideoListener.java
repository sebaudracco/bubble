package com.mopub.mobileads;

import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubRewardedAd.MoPubRewardedAdListener;

class MoPubRewardedVideo$MoPubRewardedVideoListener extends MoPubRewardedAdListener implements RewardedVastVideoInterstitial$RewardedVideoInterstitialListener {
    final /* synthetic */ MoPubRewardedVideo this$0;

    public MoPubRewardedVideo$MoPubRewardedVideoListener(MoPubRewardedVideo moPubRewardedVideo) {
        this.this$0 = moPubRewardedVideo;
        super(moPubRewardedVideo, MoPubRewardedVideo.class);
    }

    public void onVideoComplete() {
        if (this.this$0.getRewardedAdCurrencyName() == null) {
            MoPubLog.m12061d("No rewarded video was loaded, so no reward is possible");
        } else {
            MoPubRewardedVideoManager.onRewardedVideoCompleted(this.mCustomEventClass, this.this$0.getAdNetworkId(), MoPubReward.success(this.this$0.getRewardedAdCurrencyName(), this.this$0.getRewardedAdCurrencyAmount()));
        }
    }
}
