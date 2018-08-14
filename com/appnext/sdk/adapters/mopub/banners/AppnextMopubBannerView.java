package com.appnext.sdk.adapters.mopub.banners;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import com.appnext.banners.BannerView;
import com.appnext.banners.C1005d;

public class AppnextMopubBannerView extends BannerView {
    public AppnextMopubBannerView(Context context) {
        super(context);
    }

    public AppnextMopubBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AppnextMopubBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public AppnextMopubBannerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected C1005d getBannerAdapter() {
        if (this.bannerAdapter == null) {
            this.bannerAdapter = new AppnextMoPubBannerAdapter();
        }
        return this.bannerAdapter;
    }
}
