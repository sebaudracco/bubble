package com.mopub.mobileads;

public interface MoPubView$BannerAdListener {
    void onBannerClicked(MoPubView moPubView);

    void onBannerCollapsed(MoPubView moPubView);

    void onBannerExpanded(MoPubView moPubView);

    void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode);

    void onBannerLoaded(MoPubView moPubView);
}
