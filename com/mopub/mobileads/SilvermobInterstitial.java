package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.silvermob.sdk.InterstitialAd;
import com.silvermob.sdk.InterstitialAd.InterstitialAdLoadListener;
import com.silvermob.sdk.InterstitialAd.InterstitialListener;
import java.util.Map;
import java.util.logging.Logger;

@Keep
public class SilvermobInterstitial extends CustomEventInterstitial implements InterstitialAdLoadListener, InterstitialListener {
    private static final String PLACEMENT_ID_KEY = "placement_id";
    private InterstitialAd ad;
    private CustomEventInterstitialListener listener;
    private Logger logger = Logger.getLogger("SilvermobMopubConnector");

    protected void loadInterstitial(@Nullable Context context, @Nullable CustomEventInterstitialListener listener, @Nullable Map<String, Object> localExtras, @Nullable Map<String, String> serverExtras) {
        this.listener = listener;
        if (listener == null) {
            this.logger.warning("Failed to request banner ad, [listener] is null");
            return;
        }
        this.listener = listener;
        if (context == null) {
            this.logger.warning("Failed to request banner ad, [context] is null");
            listener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        } else if (!(context instanceof Activity)) {
            this.logger.warning("Failed to request banner ad, [context] is not an Activity instance");
            listener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        } else if (localExtras == null) {
            this.logger.warning("Failed to request banner ad, [localExtras] is null");
            listener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        } else if (serverExtras == null) {
            this.logger.warning("Failed to request banner ad, [serverExtras] is null");
            listener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        } else {
            String placementId = serverExtras.containsKey(PLACEMENT_ID_KEY) ? (String) serverExtras.get(PLACEMENT_ID_KEY) : "";
            if (TextUtils.isEmpty(placementId)) {
                this.logger.warning("Failed to request banner ad, [adUnitId] is empty");
                listener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
                return;
            }
            this.ad = new InterstitialAd(context);
            this.ad.requestInterstitialAd(placementId, this);
        }
    }

    protected void showInterstitial() {
        if (this.ad != null) {
            this.ad.showInterstitialAd(this);
        }
    }

    protected void onInvalidate() {
    }

    public void onAdLoaded() {
        if (this.listener != null) {
            this.listener.onInterstitialLoaded();
        }
    }

    public void onNoAd() {
        if (this.listener != null) {
            this.listener.onInterstitialFailed(MoPubErrorCode.NO_FILL);
        }
    }

    public void onShown() {
        if (this.listener != null) {
            this.listener.onInterstitialShown();
        }
    }

    public void onClosed() {
        if (this.listener != null) {
            this.listener.onInterstitialDismissed();
        }
    }

    public void onClicked() {
        if (this.listener != null) {
            this.listener.onInterstitialClicked();
        }
    }

    public void onError() {
        if (this.listener != null) {
            this.listener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
        }
    }
}
