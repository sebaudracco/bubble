package com.fyber.ads.videos.p093a;

/* compiled from: RewardedVideoClientStatusListener */
public interface C2446e {

    /* compiled from: RewardedVideoClientStatusListener */
    public enum C2470a {
        STARTED,
        CLOSE_FINISHED,
        CLOSE_ABORTED,
        PENDING_CLOSE,
        ERROR
    }

    void didChangeStatus(C2470a c2470a);
}
