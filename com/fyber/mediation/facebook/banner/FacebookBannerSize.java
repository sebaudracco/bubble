package com.fyber.mediation.facebook.banner;

import com.fyber.ads.banners.BannerSize;
import com.fyber.ads.banners.BannerSize.Builder;
import cz.msebera.android.httpclient.HttpStatus;

class FacebookBannerSize {
    static final BannerSize HEIGHT_50 = Builder.newBuilder().withWidth(-1).withHeight(50).build();
    static final BannerSize HEIGHT_90 = Builder.newBuilder().withWidth(-1).withHeight(90).build();
    static final BannerSize RECTANGLE_300_250 = Builder.newBuilder().withWidth(HttpStatus.SC_MULTIPLE_CHOICES).withHeight(250).build();

    FacebookBannerSize() {
    }
}
