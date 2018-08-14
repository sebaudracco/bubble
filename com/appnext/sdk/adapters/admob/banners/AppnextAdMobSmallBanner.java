package com.appnext.sdk.adapters.admob.banners;

import android.content.Context;
import com.appnext.banners.SmallBannerAd;
import com.appnext.core.Ad;

public class AppnextAdMobSmallBanner extends SmallBannerAd {
    private static final String TID = "321";

    public AppnextAdMobSmallBanner(Context context, String str) {
        super(context, str);
    }

    protected AppnextAdMobSmallBanner(Ad ad) {
        super(ad);
    }

    public String getTID() {
        return TID;
    }
}
