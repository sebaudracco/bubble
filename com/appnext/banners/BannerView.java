package com.appnext.banners;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import com.appnext.core.callbacks.OnECPMLoaded;

public class BannerView extends BaseBannerView {
    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public BannerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected C1005d getBannerAdapter() {
        if (this.bannerAdapter == null) {
            this.bannerAdapter = new C1006a();
        }
        return this.bannerAdapter;
    }

    public void getECPM(BannerAdRequest bannerAdRequest, OnECPMLoaded onECPMLoaded) {
        super.getECPM(bannerAdRequest, onECPMLoaded);
    }
}
