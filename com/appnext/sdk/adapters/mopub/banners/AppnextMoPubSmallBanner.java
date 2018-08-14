package com.appnext.sdk.adapters.mopub.banners;

import android.content.Context;
import com.appnext.banners.SmallBannerAd;
import com.appnext.core.Ad;

public class AppnextMoPubSmallBanner extends SmallBannerAd {
    private static final String TID = "311";

    public AppnextMoPubSmallBanner(Context context, String str) {
        super(context, str);
    }

    protected AppnextMoPubSmallBanner(Ad ad) {
        super(ad);
    }

    public String getTID() {
        return TID;
    }
}
