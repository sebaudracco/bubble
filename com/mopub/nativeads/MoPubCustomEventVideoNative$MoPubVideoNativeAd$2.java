package com.mopub.nativeads;

import com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd;
import com.mopub.nativeads.NativeImageHelper.ImageListener;

class MoPubCustomEventVideoNative$MoPubVideoNativeAd$2 implements ImageListener {
    final /* synthetic */ MoPubVideoNativeAd this$0;

    MoPubCustomEventVideoNative$MoPubVideoNativeAd$2(MoPubVideoNativeAd this$0) {
        this.this$0 = this$0;
    }

    public void onImagesCached() {
        MoPubVideoNativeAd.access$400(this.this$0).prepareVastVideoConfiguration(this.this$0.getVastVideo(), this.this$0, MoPubVideoNativeAd.access$200(this.this$0) == null ? null : MoPubVideoNativeAd.access$200(this.this$0).getDspCreativeId(), MoPubVideoNativeAd.access$300(this.this$0));
    }

    public void onImagesFailedToCache(NativeErrorCode errorCode) {
        MoPubVideoNativeAd.access$500(this.this$0).onNativeAdFailed(errorCode);
    }
}
