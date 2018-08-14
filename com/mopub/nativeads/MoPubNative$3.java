package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.network.AdResponse;

class MoPubNative$3 implements CustomEventNativeListener {
    final /* synthetic */ MoPubNative this$0;
    final /* synthetic */ AdResponse val$response;

    MoPubNative$3(MoPubNative this$0, AdResponse adResponse) {
        this.this$0 = this$0;
        this.val$response = adResponse;
    }

    public void onNativeAdLoaded(@NonNull BaseNativeAd nativeAd) {
        Context context = this.this$0.getContextOrDestroy();
        if (context != null) {
            MoPubAdRenderer renderer = this.this$0.mAdRendererRegistry.getRendererForAd(nativeAd);
            if (renderer == null) {
                onNativeAdFailed(NativeErrorCode.NATIVE_RENDERER_CONFIGURATION_ERROR);
            } else {
                MoPubNative.access$200(this.this$0).onNativeLoad(new NativeAd(context, this.val$response.getImpressionTrackingUrl(), this.val$response.getClickTrackingUrl(), MoPubNative.access$100(this.this$0), nativeAd, renderer));
            }
        }
    }

    public void onNativeAdFailed(NativeErrorCode errorCode) {
        MoPubLog.m12067v(String.format("Native Ad failed to load with error: %s.", new Object[]{errorCode}));
        this.this$0.requestNativeAd(this.val$response.getFailoverUrl());
    }
}
