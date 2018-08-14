package com.integralads.avid.library.mopub.video;

public interface AvidVideoPlaybackListener {
    void recordAdClickThruEvent();

    void recordAdCompleteEvent();

    void recordAdDurationChangeEvent(String str, String str2);

    void recordAdEnteredFullscreenEvent();

    void recordAdError(String str);

    void recordAdExitedFullscreenEvent();

    void recordAdExpandedChangeEvent();

    void recordAdImpressionEvent();

    void recordAdLoadedEvent();

    void recordAdPausedEvent();

    void recordAdPlayingEvent();

    void recordAdSkippedEvent();

    void recordAdStartedEvent();

    void recordAdStoppedEvent();

    void recordAdUserAcceptInvitationEvent();

    void recordAdUserCloseEvent();

    void recordAdUserMinimizeEvent();

    void recordAdVideoFirstQuartileEvent();

    void recordAdVideoMidpointEvent();

    void recordAdVideoStartEvent();

    void recordAdVideoThirdQuartileEvent();

    void recordAdVolumeChangeEvent(Integer num);
}
