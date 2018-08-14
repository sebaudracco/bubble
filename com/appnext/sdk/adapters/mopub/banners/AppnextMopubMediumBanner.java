package com.appnext.sdk.adapters.mopub.banners;

import android.content.Context;
import com.appnext.banners.MediumRectangleAd;
import com.appnext.core.Ad;

public class AppnextMopubMediumBanner extends MediumRectangleAd {
    private static final String TID = "311";

    public String getTID() {
        return TID;
    }

    public AppnextMopubMediumBanner(Context context, String str) {
        super(context, str);
    }

    protected AppnextMopubMediumBanner(Ad ad) {
        super(ad);
    }
}
