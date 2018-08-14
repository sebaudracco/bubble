package com.fyber.mediation.admob.banner;

import android.view.View;
import com.fyber.ads.banners.mediation.BannerWrapper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;

class AdMobBannerWrapper extends BannerWrapper {
    private AdView bannerView;

    class C25771 extends AdListener {
        C25771() {
        }

        public void onAdLoaded() {
            AdMobBannerWrapper.this.onBannerLoaded();
        }

        public void onAdFailedToLoad(int errorCode) {
            switch (errorCode) {
                case 0:
                    AdMobBannerWrapper.this.onBannerError("Error: internal error");
                    return;
                case 1:
                    AdMobBannerWrapper.this.onBannerError("Error: invalid requests");
                    return;
                case 2:
                    AdMobBannerWrapper.this.onBannerError("Error: network error");
                    return;
                case 3:
                    AdMobBannerWrapper.this.onBannerError("Error: no fill");
                    return;
                default:
                    AdMobBannerWrapper.this.onBannerError("Unknown error");
                    return;
            }
        }

        public void onAdOpened() {
            AdMobBannerWrapper.this.onBannerClick();
        }

        public void onAdClosed() {
        }

        public void onAdLeftApplication() {
            AdMobBannerWrapper.this.onBannerLeftApplication();
        }
    }

    AdMobBannerWrapper(AdView bannerView) {
        this.bannerView = bannerView;
        bannerView.setAdListener(new C25771());
    }

    public View getView() {
        return this.bannerView;
    }

    public void destroy() {
        this.bannerView.destroy();
    }
}
