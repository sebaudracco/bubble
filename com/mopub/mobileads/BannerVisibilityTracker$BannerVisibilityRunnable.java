package com.mopub.mobileads;

class BannerVisibilityTracker$BannerVisibilityRunnable implements Runnable {
    final /* synthetic */ BannerVisibilityTracker this$0;

    BannerVisibilityTracker$BannerVisibilityRunnable(BannerVisibilityTracker this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        if (!BannerVisibilityTracker.access$000(this.this$0)) {
            BannerVisibilityTracker.access$102(this.this$0, false);
            if (BannerVisibilityTracker.access$400(this.this$0).isVisible(BannerVisibilityTracker.access$200(this.this$0), BannerVisibilityTracker.access$300(this.this$0))) {
                if (!BannerVisibilityTracker.access$400(this.this$0).hasBeenVisibleYet()) {
                    BannerVisibilityTracker.access$400(this.this$0).setStartTimeMillis();
                }
                if (BannerVisibilityTracker.access$400(this.this$0).hasRequiredTimeElapsed() && BannerVisibilityTracker.access$500(this.this$0) != null) {
                    BannerVisibilityTracker.access$500(this.this$0).onVisibilityChanged();
                    BannerVisibilityTracker.access$002(this.this$0, true);
                }
            }
            if (!BannerVisibilityTracker.access$000(this.this$0)) {
                this.this$0.scheduleVisibilityCheck();
            }
        }
    }
}
