package com.mopub.nativeads;

import android.view.View;
import android.view.View.OnClickListener;
import com.mopub.mobileads.MraidVideoPlayerActivity;
import com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd;

class MoPubCustomEventVideoNative$MoPubVideoNativeAd$6 implements OnClickListener {
    final /* synthetic */ MoPubVideoNativeAd this$0;

    MoPubCustomEventVideoNative$MoPubVideoNativeAd$6(MoPubVideoNativeAd this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        MoPubVideoNativeAd.access$1300(this.this$0);
        MoPubVideoNativeAd.access$600(this.this$0).triggerImpressionTrackers();
        MraidVideoPlayerActivity.startNativeVideo(MoPubVideoNativeAd.access$300(this.this$0), MoPubVideoNativeAd.access$1400(this.this$0), this.this$0.mVastVideoConfig);
    }
}
