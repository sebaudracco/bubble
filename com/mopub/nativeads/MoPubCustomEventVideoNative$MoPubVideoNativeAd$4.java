package com.mopub.nativeads;

import android.view.View;
import android.view.View.OnClickListener;
import com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd;

class MoPubCustomEventVideoNative$MoPubVideoNativeAd$4 implements OnClickListener {
    final /* synthetic */ MoPubVideoNativeAd this$0;

    MoPubCustomEventVideoNative$MoPubVideoNativeAd$4(MoPubVideoNativeAd this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        MoPubVideoNativeAd.access$700(this.this$0).resetProgress();
        MoPubVideoNativeAd.access$600(this.this$0).seekTo(0);
        MoPubVideoNativeAd.access$902(this.this$0, false);
        MoPubVideoNativeAd.access$1102(this.this$0, false);
    }
}
