package com.appsgeyser.sdk.ads.fastTrack;

final /* synthetic */ class FastTrackAdsController$$Lambda$4 implements Runnable {
    private final FastTrackAdsController arg$1;
    private final String arg$2;

    private FastTrackAdsController$$Lambda$4(FastTrackAdsController fastTrackAdsController, String str) {
        this.arg$1 = fastTrackAdsController;
        this.arg$2 = str;
    }

    public static Runnable lambdaFactory$(FastTrackAdsController fastTrackAdsController, String str) {
        return new FastTrackAdsController$$Lambda$4(fastTrackAdsController, str);
    }

    public void run() {
        FastTrackAdsController.lambda$showFullscreen$2(this.arg$1, this.arg$2);
    }
}
