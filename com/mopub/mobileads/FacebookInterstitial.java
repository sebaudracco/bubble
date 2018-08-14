package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

public class FacebookInterstitial extends CustomEventInterstitial implements InterstitialAdListener {
    private static final int ONE_HOURS_MILLIS = 3600000;
    private static final String PLACEMENT_ID_KEY = "placement_id";
    private Runnable mAdExpiration = new 1(this);
    private InterstitialAd mFacebookInterstitial;
    @NonNull
    private Handler mHandler = new Handler();
    private CustomEventInterstitialListener mInterstitialListener;

    protected void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> serverExtras) {
        MoPubLog.d("Loading Facebook interstitial");
        this.mInterstitialListener = customEventInterstitialListener;
        if (extrasAreValid(serverExtras)) {
            String placementId = (String) serverExtras.get(PLACEMENT_ID_KEY);
            AdSettings.setMediationService("MOPUB_4.20.0");
            this.mFacebookInterstitial = new InterstitialAd(context, placementId);
            this.mFacebookInterstitial.setAdListener(this);
            this.mFacebookInterstitial.loadAd();
        } else if (this.mInterstitialListener != null) {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        }
    }

    protected void showInterstitial() {
        if (this.mFacebookInterstitial == null || !this.mFacebookInterstitial.isAdLoaded()) {
            MoPubLog.d("Tried to show a Facebook interstitial ad when it's not ready. Please try again.");
            if (this.mInterstitialListener != null) {
                onError(this.mFacebookInterstitial, AdError.INTERNAL_ERROR);
                return;
            } else {
                MoPubLog.d("Interstitial listener not instantiated. Please load interstitial again.");
                return;
            }
        }
        this.mFacebookInterstitial.show();
        cancelExpirationTimer();
    }

    protected void onInvalidate() {
        cancelExpirationTimer();
        if (this.mFacebookInterstitial != null) {
            this.mFacebookInterstitial.destroy();
            this.mFacebookInterstitial = null;
            this.mInterstitialListener = null;
        }
    }

    public void onAdLoaded(Ad ad) {
        cancelExpirationTimer();
        MoPubLog.d("Facebook interstitial ad loaded successfully.");
        if (this.mInterstitialListener != null) {
            this.mInterstitialListener.onInterstitialLoaded();
        }
        this.mHandler.postDelayed(this.mAdExpiration, 3600000);
    }

    public void onError(Ad ad, AdError error) {
        cancelExpirationTimer();
        MoPubLog.d("Facebook interstitial ad failed to load.");
        if (this.mInterstitialListener == null) {
            return;
        }
        if (error == AdError.NO_FILL) {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_NO_FILL);
        } else if (error == AdError.INTERNAL_ERROR) {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
        } else {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.UNSPECIFIED);
        }
    }

    public void onInterstitialDisplayed(Ad ad) {
        cancelExpirationTimer();
        MoPubLog.d("Showing Facebook interstitial ad.");
        if (this.mInterstitialListener != null) {
            this.mInterstitialListener.onInterstitialShown();
        }
    }

    public void onAdClicked(Ad ad) {
        MoPubLog.d("Facebook interstitial ad clicked.");
        if (this.mInterstitialListener != null) {
            this.mInterstitialListener.onInterstitialClicked();
        }
    }

    public void onLoggingImpression(Ad ad) {
        MoPubLog.d("Facebook interstitial ad logged impression.");
    }

    public void onInterstitialDismissed(Ad ad) {
        MoPubLog.d("Facebook interstitial ad dismissed.");
        if (this.mInterstitialListener != null) {
            this.mInterstitialListener.onInterstitialDismissed();
        }
    }

    private boolean extrasAreValid(Map<String, String> serverExtras) {
        String placementId = (String) serverExtras.get(PLACEMENT_ID_KEY);
        return placementId != null && placementId.length() > 0;
    }

    private void cancelExpirationTimer() {
        this.mHandler.removeCallbacks(this.mAdExpiration);
    }

    @Deprecated
    InterstitialAd getInterstitialAd() {
        return this.mFacebookInterstitial;
    }
}
