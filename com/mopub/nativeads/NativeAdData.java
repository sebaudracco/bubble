package com.mopub.nativeads;

import android.support.annotation.NonNull;

class NativeAdData {
    @NonNull
    private final MoPubAdRenderer adRenderer;
    @NonNull
    private final NativeAd adResponse;
    @NonNull
    private final String adUnitId;

    NativeAdData(@NonNull String adUnitId, @NonNull MoPubAdRenderer adRenderer, @NonNull NativeAd adResponse) {
        this.adUnitId = adUnitId;
        this.adRenderer = adRenderer;
        this.adResponse = adResponse;
    }

    @NonNull
    String getAdUnitId() {
        return this.adUnitId;
    }

    @NonNull
    MoPubAdRenderer getAdRenderer() {
        return this.adRenderer;
    }

    @NonNull
    NativeAd getAd() {
        return this.adResponse;
    }
}
