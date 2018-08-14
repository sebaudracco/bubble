package com.appnext.sdk.adapters.mopub.banners;

import android.content.Context;
import com.appnext.banners.C1006a;
import com.appnext.core.Ad;

public class AppnextMoPubBannerAdapter extends C1006a {
    protected Ad createAd(Context context, String str) {
        String bannerSize = getBannerSize().toString();
        Object obj = -1;
        switch (bannerSize.hashCode()) {
            case -1966536496:
                if (bannerSize.equals("LARGE_BANNER")) {
                    obj = 1;
                    break;
                }
                break;
            case -96588539:
                if (bannerSize.equals("MEDIUM_RECTANGLE")) {
                    obj = 2;
                    break;
                }
                break;
            case 1951953708:
                if (bannerSize.equals("BANNER")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new AppnextMoPubSmallBanner(context, str);
            case 1:
                return new AppnextMoPubLargeBanner(context, str);
            case 2:
                return new AppnextMopubMediumBanner(context, str);
            default:
                throw new IllegalArgumentException("Wrong banner size " + getBannerSize());
        }
    }
}
