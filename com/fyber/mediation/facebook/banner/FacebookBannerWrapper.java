package com.fyber.mediation.facebook.banner;

import android.view.View;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdView;
import com.fyber.ads.banners.mediation.BannerWrapper;

public class FacebookBannerWrapper extends BannerWrapper implements AdListener {
    private AdView bannerView;

    public FacebookBannerWrapper(AdView bannerView) {
        this.bannerView = bannerView;
        bannerView.setAdListener(this);
    }

    public View getView() {
        return this.bannerView;
    }

    public void destroy() {
        this.bannerView.destroy();
    }

    public void onError(Ad ad, AdError adError) {
        onBannerError("Facebook ad error (" + adError.getErrorCode() + "): " + adError.getErrorMessage());
    }

    public void onAdLoaded(Ad ad) {
        onBannerLoaded();
    }

    public void onAdClicked(Ad ad) {
        onBannerClick();
    }

    public void onLoggingImpression(Ad ad) {
    }
}
