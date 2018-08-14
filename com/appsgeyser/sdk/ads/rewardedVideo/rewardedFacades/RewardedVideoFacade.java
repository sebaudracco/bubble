package com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades;

import java.io.Serializable;

public interface RewardedVideoFacade {

    public interface RewardedVideoListener extends Serializable {
        void onVideoClicked();

        void onVideoClosed();

        void onVideoError(String str);

        void onVideoFinished();

        void onVideoLoaded();

        void onVideoOpened();
    }

    boolean isVideoLoaded();

    void loadRewardedVideo();

    void onDestroy();

    void onPause();

    void onResume();

    void setListener(RewardedVideoListener rewardedVideoListener);

    void showRewardedVideo();
}
