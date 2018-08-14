package com.mopub.mobileads;

class CustomEventBannerAdapter$2 implements BannerVisibilityTracker$BannerVisibilityTrackerListener {
    final /* synthetic */ CustomEventBannerAdapter this$0;

    CustomEventBannerAdapter$2(CustomEventBannerAdapter this$0) {
        this.this$0 = this$0;
    }

    public void onVisibilityChanged() {
        CustomEventBannerAdapter.access$000(this.this$0).trackNativeImpression();
        if (CustomEventBannerAdapter.access$100(this.this$0) != null) {
            CustomEventBannerAdapter.access$100(this.this$0).trackMpxAndThirdPartyImpressions();
        }
    }
}
