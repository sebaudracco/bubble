package com.mopub.nativeads;

import android.support.annotation.NonNull;

class NativeAdSource$2 implements MoPubNative$MoPubNativeNetworkListener {
    final /* synthetic */ NativeAdSource this$0;

    NativeAdSource$2(NativeAdSource this$0) {
        this.this$0 = this$0;
    }

    public void onNativeLoad(@NonNull NativeAd nativeAd) {
        if (NativeAdSource.access$000(this.this$0) != null) {
            this.this$0.mRequestInFlight = false;
            NativeAdSource nativeAdSource = this.this$0;
            nativeAdSource.mSequenceNumber++;
            this.this$0.resetRetryTime();
            NativeAdSource.access$100(this.this$0).add(new TimestampWrapper(nativeAd));
            if (NativeAdSource.access$100(this.this$0).size() == 1 && NativeAdSource.access$200(this.this$0) != null) {
                NativeAdSource.access$200(this.this$0).onAdsAvailable();
            }
            this.this$0.replenishCache();
        }
    }

    public void onNativeFail(NativeErrorCode errorCode) {
        this.this$0.mRequestInFlight = false;
        if (this.this$0.mCurrentRetries >= NativeAdSource.RETRY_TIME_ARRAY_MILLISECONDS.length - 1) {
            this.this$0.resetRetryTime();
            return;
        }
        this.this$0.updateRetryTime();
        this.this$0.mRetryInFlight = true;
        NativeAdSource.access$400(this.this$0).postDelayed(NativeAdSource.access$300(this.this$0), (long) this.this$0.getRetryTime());
    }
}
