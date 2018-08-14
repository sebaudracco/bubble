package com.appsgeyser.sdk.ads.fastTrack.adapters;

final /* synthetic */ class FastTrackMopubAdapter$$Lambda$3 implements Runnable {
    private final FastTrackMopubAdapter arg$1;

    private FastTrackMopubAdapter$$Lambda$3(FastTrackMopubAdapter fastTrackMopubAdapter) {
        this.arg$1 = fastTrackMopubAdapter;
    }

    public static Runnable lambdaFactory$(FastTrackMopubAdapter fastTrackMopubAdapter) {
        return new FastTrackMopubAdapter$$Lambda$3(fastTrackMopubAdapter);
    }

    public void run() {
        FastTrackMopubAdapter.lambda$showFullscreen$0(this.arg$1);
    }
}
