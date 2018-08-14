package com.appsgeyser.sdk.ads.fastTrack.adapters;

final /* synthetic */ class FastTrackMopubAdapter$$Lambda$2 implements Runnable {
    private final FastTrackMopubAdapter arg$1;

    private FastTrackMopubAdapter$$Lambda$2(FastTrackMopubAdapter fastTrackMopubAdapter) {
        this.arg$1 = fastTrackMopubAdapter;
    }

    public static Runnable lambdaFactory$(FastTrackMopubAdapter fastTrackMopubAdapter) {
        return new FastTrackMopubAdapter$$Lambda$2(fastTrackMopubAdapter);
    }

    public void run() {
        FastTrackMopubAdapter.lambda$new$2(this.arg$1);
    }
}
