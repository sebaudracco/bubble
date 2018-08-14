package com.appsgeyser.sdk.ads.fastTrack;

import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import rx.functions.Action1;

final /* synthetic */ class FastTrackAdsController$$Lambda$1 implements Action1 {
    private final FastTrackAdsController arg$1;

    private FastTrackAdsController$$Lambda$1(FastTrackAdsController fastTrackAdsController) {
        this.arg$1 = fastTrackAdsController;
    }

    public static Action1 lambdaFactory$(FastTrackAdsController fastTrackAdsController) {
        return new FastTrackAdsController$$Lambda$1(fastTrackAdsController);
    }

    public void call(Object obj) {
        this.arg$1.init((ConfigPhp) obj);
    }
}
