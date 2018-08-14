package com.appsgeyser.sdk.ads.fastTrack.adapters;

import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackMopubAdapter.C12463;

final /* synthetic */ class FastTrackMopubAdapter$3$$Lambda$4 implements Runnable {
    private final C12463 arg$1;

    private FastTrackMopubAdapter$3$$Lambda$4(C12463 c12463) {
        this.arg$1 = c12463;
    }

    public static Runnable lambdaFactory$(C12463 c12463) {
        return new FastTrackMopubAdapter$3$$Lambda$4(c12463);
    }

    public void run() {
        this.arg$1.this$0.loadFullscreen();
    }
}
