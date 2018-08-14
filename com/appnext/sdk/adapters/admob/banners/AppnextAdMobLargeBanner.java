package com.appnext.sdk.adapters.admob.banners;

import android.content.Context;
import com.appnext.banners.LargeBannerAd;
import com.appnext.core.Ad;

public class AppnextAdMobLargeBanner extends LargeBannerAd {
    private static final String TID = "321";

    public String getTID() {
        return TID;
    }

    public AppnextAdMobLargeBanner(Context context, String str) {
        super(context, str);
    }

    protected AppnextAdMobLargeBanner(Ad ad) {
        super(ad);
    }
}
