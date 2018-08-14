package com.mopub.mobileads;

import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubRewardedAd.MoPubRewardedAdListener;
import com.mopub.mraid.RewardedMraidInterstitial$RewardedMraidInterstitialListener;

class MoPubRewardedPlayable$MoPubRewardedPlayableListener extends MoPubRewardedAdListener implements RewardedMraidInterstitial$RewardedMraidInterstitialListener {
    final /* synthetic */ MoPubRewardedPlayable this$0;

    public MoPubRewardedPlayable$MoPubRewardedPlayableListener(MoPubRewardedPlayable moPubRewardedPlayable) {
        this.this$0 = moPubRewardedPlayable;
        super(moPubRewardedPlayable, MoPubRewardedPlayable.class);
    }

    public void onMraidComplete() {
        if (this.this$0.getRewardedAdCurrencyName() == null) {
            MoPubLog.m12061d("No rewarded video was loaded, so no reward is possible");
        } else {
            MoPubRewardedVideoManager.onRewardedVideoCompleted(this.mCustomEventClass, this.this$0.getAdNetworkId(), MoPubReward.success(this.this$0.getRewardedAdCurrencyName(), this.this$0.getRewardedAdCurrencyAmount()));
        }
    }
}
