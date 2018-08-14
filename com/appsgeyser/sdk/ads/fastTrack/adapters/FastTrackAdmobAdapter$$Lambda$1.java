package com.appsgeyser.sdk.ads.fastTrack.adapters;

final /* synthetic */ class FastTrackAdmobAdapter$$Lambda$1 implements Runnable {
    private final FastTrackAdmobAdapter arg$1;

    private FastTrackAdmobAdapter$$Lambda$1(FastTrackAdmobAdapter fastTrackAdmobAdapter) {
        this.arg$1 = fastTrackAdmobAdapter;
    }

    public static Runnable lambdaFactory$(FastTrackAdmobAdapter fastTrackAdmobAdapter) {
        return new FastTrackAdmobAdapter$$Lambda$1(fastTrackAdmobAdapter);
    }

    public void run() {
        FastTrackAdmobAdapter.lambda$new$1(this.arg$1);
    }
}
