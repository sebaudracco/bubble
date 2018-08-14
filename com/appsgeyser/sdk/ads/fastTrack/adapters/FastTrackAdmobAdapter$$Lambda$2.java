package com.appsgeyser.sdk.ads.fastTrack.adapters;

final /* synthetic */ class FastTrackAdmobAdapter$$Lambda$2 implements Runnable {
    private final FastTrackAdmobAdapter arg$1;

    private FastTrackAdmobAdapter$$Lambda$2(FastTrackAdmobAdapter fastTrackAdmobAdapter) {
        this.arg$1 = fastTrackAdmobAdapter;
    }

    public static Runnable lambdaFactory$(FastTrackAdmobAdapter fastTrackAdmobAdapter) {
        return new FastTrackAdmobAdapter$$Lambda$2(fastTrackAdmobAdapter);
    }

    public void run() {
        FastTrackAdmobAdapter.lambda$new$2(this.arg$1);
    }
}
