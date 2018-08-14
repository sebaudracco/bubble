package com.appsgeyser.sdk.ads.fastTrack;

import android.view.ViewGroup;
import rx.functions.Action1;

final /* synthetic */ class FastTrackAdsController$$Lambda$3 implements Action1 {
    private final FastTrackAdsController arg$1;

    private FastTrackAdsController$$Lambda$3(FastTrackAdsController fastTrackAdsController) {
        this.arg$1 = fastTrackAdsController;
    }

    public static Action1 lambdaFactory$(FastTrackAdsController fastTrackAdsController) {
        return new FastTrackAdsController$$Lambda$3(fastTrackAdsController);
    }

    public void call(Object obj) {
        this.arg$1.adsAdapter.initBannerView((ViewGroup) obj);
    }
}
