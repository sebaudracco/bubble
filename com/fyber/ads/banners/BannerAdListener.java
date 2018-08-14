package com.fyber.ads.banners;

public interface BannerAdListener {
    void onAdClicked(BannerAd bannerAd);

    void onAdError(BannerAd bannerAd, String str);

    void onAdLeftApplication(BannerAd bannerAd);

    void onAdLoaded(BannerAd bannerAd);
}
