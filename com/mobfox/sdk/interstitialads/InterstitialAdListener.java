package com.mobfox.sdk.interstitialads;

public interface InterstitialAdListener {
    void onInterstitialClicked(InterstitialAd interstitialAd);

    void onInterstitialClosed(InterstitialAd interstitialAd);

    void onInterstitialFailed(InterstitialAd interstitialAd, Exception exception);

    void onInterstitialFinished();

    void onInterstitialLoaded(InterstitialAd interstitialAd);

    void onInterstitialShown(InterstitialAd interstitialAd);
}
