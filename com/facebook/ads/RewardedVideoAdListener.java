package com.facebook.ads;

public interface RewardedVideoAdListener extends AdListener {
    void onLoggingImpression(Ad ad);

    void onRewardedVideoClosed();

    void onRewardedVideoCompleted();
}
