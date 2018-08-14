package com.mopub.mobileads;

import com.mopub.common.MoPubReward;

class MoPubRewardedVideoManager$13 implements Runnable {
    final /* synthetic */ String val$currentlyShowingAdUnitId;
    final /* synthetic */ String val$serverCompletionUrl;

    MoPubRewardedVideoManager$13(String str, String str2) {
        this.val$currentlyShowingAdUnitId = str;
        this.val$serverCompletionUrl = str2;
    }

    public void run() {
        String rewardName;
        String rewardAmount;
        String className;
        MoPubReward reward = MoPubRewardedVideoManager.access$1000(MoPubRewardedVideoManager.access$200()).getMoPubReward(this.val$currentlyShowingAdUnitId);
        if (reward == null) {
            rewardName = "";
        } else {
            rewardName = reward.getLabel();
        }
        if (reward == null) {
            rewardAmount = Integer.toString(0);
        } else {
            rewardAmount = Integer.toString(reward.getAmount());
        }
        CustomEventRewardedAd customEvent = MoPubRewardedVideoManager.access$1000(MoPubRewardedVideoManager.access$200()).getCustomEvent(this.val$currentlyShowingAdUnitId);
        if (customEvent == null || customEvent.getClass() == null) {
            className = null;
        } else {
            className = customEvent.getClass().getName();
        }
        RewardedVideoCompletionRequestHandler.makeRewardedVideoCompletionRequest(MoPubRewardedVideoManager.access$1100(MoPubRewardedVideoManager.access$200()), this.val$serverCompletionUrl, MoPubRewardedVideoManager.access$1000(MoPubRewardedVideoManager.access$200()).getCustomerId(), rewardName, rewardAmount, className, MoPubRewardedVideoManager.access$1000(MoPubRewardedVideoManager.access$200()).getCustomData(this.val$currentlyShowingAdUnitId));
    }
}
