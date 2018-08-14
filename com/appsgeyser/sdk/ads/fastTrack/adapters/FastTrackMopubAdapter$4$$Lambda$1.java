package com.appsgeyser.sdk.ads.fastTrack.adapters;

import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackMopubAdapter.C12474;

final /* synthetic */ class FastTrackMopubAdapter$4$$Lambda$1 implements Runnable {
    private final C12474 arg$1;

    private FastTrackMopubAdapter$4$$Lambda$1(C12474 c12474) {
        this.arg$1 = c12474;
    }

    public static Runnable lambdaFactory$(C12474 c12474) {
        return new FastTrackMopubAdapter$4$$Lambda$1(c12474);
    }

    public void run() {
        this.arg$1.this$0.loadRewardedVideo();
    }
}
