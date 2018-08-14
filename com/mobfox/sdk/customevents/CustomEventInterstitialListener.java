package com.mobfox.sdk.customevents;

public interface CustomEventInterstitialListener {
    void onInterstitialClicked(CustomEventInterstitial customEventInterstitial);

    void onInterstitialClosed(CustomEventInterstitial customEventInterstitial);

    void onInterstitialFailed(CustomEventInterstitial customEventInterstitial, Exception exception);

    void onInterstitialFinished();

    void onInterstitialLoaded(CustomEventInterstitial customEventInterstitial);

    void onInterstitialShown(CustomEventInterstitial customEventInterstitial);
}
