package com.mopub.mobileads;

import com.mopub.common.logging.MoPubLog;

class CustomEventBannerAdapter$1 implements Runnable {
    final /* synthetic */ CustomEventBannerAdapter this$0;

    CustomEventBannerAdapter$1(CustomEventBannerAdapter this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        MoPubLog.m12061d("Third-party network timed out.");
        this.this$0.onBannerFailed(MoPubErrorCode.NETWORK_TIMEOUT);
        this.this$0.invalidate();
    }
}
