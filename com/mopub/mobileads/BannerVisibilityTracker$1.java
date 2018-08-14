package com.mopub.mobileads;

import android.view.ViewTreeObserver.OnPreDrawListener;

class BannerVisibilityTracker$1 implements OnPreDrawListener {
    final /* synthetic */ BannerVisibilityTracker this$0;

    BannerVisibilityTracker$1(BannerVisibilityTracker this$0) {
        this.this$0 = this$0;
    }

    public boolean onPreDraw() {
        this.this$0.scheduleVisibilityCheck();
        return true;
    }
}
