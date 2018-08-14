package com.appsgeyser.sdk.ads.fastTrack.adapters;

final /* synthetic */ class FastTrackAdmobAdapter$$Lambda$3 implements Runnable {
    private final FastTrackAdmobAdapter arg$1;

    private FastTrackAdmobAdapter$$Lambda$3(FastTrackAdmobAdapter fastTrackAdmobAdapter) {
        this.arg$1 = fastTrackAdmobAdapter;
    }

    public static Runnable lambdaFactory$(FastTrackAdmobAdapter fastTrackAdmobAdapter) {
        return new FastTrackAdmobAdapter$$Lambda$3(fastTrackAdmobAdapter);
    }

    public void run() {
        FastTrackAdmobAdapter.lambda$showFullscreen$0(this.arg$1);
    }
}
