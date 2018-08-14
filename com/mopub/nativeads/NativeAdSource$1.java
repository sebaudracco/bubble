package com.mopub.nativeads;

class NativeAdSource$1 implements Runnable {
    final /* synthetic */ NativeAdSource this$0;

    NativeAdSource$1(NativeAdSource this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        this.this$0.mRetryInFlight = false;
        this.this$0.replenishCache();
    }
}
