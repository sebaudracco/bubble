package com.appsgeyser.sdk.ads.fastTrack.adapters;

import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackAdmobAdapter.C12363;

final /* synthetic */ class FastTrackAdmobAdapter$3$$Lambda$1 implements Runnable {
    private final C12363 arg$1;

    private FastTrackAdmobAdapter$3$$Lambda$1(C12363 c12363) {
        this.arg$1 = c12363;
    }

    public static Runnable lambdaFactory$(C12363 c12363) {
        return new FastTrackAdmobAdapter$3$$Lambda$1(c12363);
    }

    public void run() {
        this.arg$1.this$0.loadFullscreen();
    }
}
