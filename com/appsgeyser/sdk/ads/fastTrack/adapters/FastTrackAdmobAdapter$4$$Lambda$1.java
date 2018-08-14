package com.appsgeyser.sdk.ads.fastTrack.adapters;

import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackAdmobAdapter.C12374;

final /* synthetic */ class FastTrackAdmobAdapter$4$$Lambda$1 implements Runnable {
    private final C12374 arg$1;

    private FastTrackAdmobAdapter$4$$Lambda$1(C12374 c12374) {
        this.arg$1 = c12374;
    }

    public static Runnable lambdaFactory$(C12374 c12374) {
        return new FastTrackAdmobAdapter$4$$Lambda$1(c12374);
    }

    public void run() {
        this.arg$1.this$0.loadRewardedVideo();
    }
}
