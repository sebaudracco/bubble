package com.mopub.nativeads;

import android.view.View;
import com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd;
import java.util.List;

class MoPubCustomEventVideoNative$MoPubVideoNativeAd$1 implements VisibilityTrackerListener {
    final /* synthetic */ MoPubVideoNativeAd this$0;

    MoPubCustomEventVideoNative$MoPubVideoNativeAd$1(MoPubVideoNativeAd this$0) {
        this.this$0 = this$0;
    }

    public void onVisibilityChanged(List<View> visibleViews, List<View> invisibleViews) {
        if (!visibleViews.isEmpty() && !MoPubVideoNativeAd.access$000(this.this$0)) {
            MoPubVideoNativeAd.access$002(this.this$0, true);
            MoPubVideoNativeAd.access$100(this.this$0);
        } else if (!invisibleViews.isEmpty() && MoPubVideoNativeAd.access$000(this.this$0)) {
            MoPubVideoNativeAd.access$002(this.this$0, false);
            MoPubVideoNativeAd.access$100(this.this$0);
        }
    }
}
