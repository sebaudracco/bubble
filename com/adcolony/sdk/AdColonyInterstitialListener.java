package com.adcolony.sdk;

public abstract class AdColonyInterstitialListener {
    public abstract void onRequestFilled(AdColonyInterstitial adColonyInterstitial);

    public void onRequestNotFilled(AdColonyZone zone) {
    }

    public void onOpened(AdColonyInterstitial ad) {
    }

    public void onClosed(AdColonyInterstitial ad) {
    }

    public void onIAPEvent(AdColonyInterstitial ad, String product_id, int engagement_type) {
    }

    public void onExpiring(AdColonyInterstitial ad) {
    }

    public void onLeftApplication(AdColonyInterstitial ad) {
    }

    public void onClicked(AdColonyInterstitial ad) {
    }

    public void onAudioStopped(AdColonyInterstitial ad) {
    }

    public void onAudioStarted(AdColonyInterstitial ad) {
    }
}
