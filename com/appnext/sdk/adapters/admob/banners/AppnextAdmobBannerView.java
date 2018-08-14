package com.appnext.sdk.adapters.admob.banners;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import com.appnext.banners.BannerView;
import com.appnext.banners.C1005d;

public class AppnextAdmobBannerView extends BannerView {
    public AppnextAdmobBannerView(Context context) {
        super(context);
    }

    public AppnextAdmobBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AppnextAdmobBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public AppnextAdmobBannerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected C1005d getBannerAdapter() {
        if (this.bannerAdapter == null) {
            this.bannerAdapter = new AdMobBannerAdapterAppnext();
        }
        return this.bannerAdapter;
    }
}
