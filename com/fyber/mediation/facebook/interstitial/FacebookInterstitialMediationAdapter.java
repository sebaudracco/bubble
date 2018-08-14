package com.fyber.mediation.facebook.interstitial;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.fyber.ads.interstitials.mediation.InterstitialMediationAdapter;
import com.fyber.mediation.facebook.FacebookMediationAdapter;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;

public class FacebookInterstitialMediationAdapter extends InterstitialMediationAdapter<FacebookMediationAdapter> implements InterstitialAdListener {
    private static final String TAG = FacebookInterstitialMediationAdapter.class.getSimpleName();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private InterstitialAd mInterstitialAd;
    private final String staticPlacementId;

    public FacebookInterstitialMediationAdapter(FacebookMediationAdapter adapter, String placementId) {
        super(adapter);
        this.staticPlacementId = placementId;
    }

    protected void show(Activity parentActivity) {
        if (this.mInterstitialAd != null) {
            this.mInterstitialAd.show();
        } else {
            interstitialError("Error: no interstitial available");
        }
    }

    protected void checkForAds(final Context context) {
        this.mHandler.post(new Runnable() {
            public void run() {
                FacebookInterstitialMediationAdapter.this.mInterstitialAd = FacebookInterstitialMediationAdapter.this.getNewInterstitial(context);
                if (FacebookInterstitialMediationAdapter.this.mInterstitialAd == null) {
                    FacebookInterstitialMediationAdapter.this.setAdError("Internal error with interstitial object creating");
                } else {
                    FacebookInterstitialMediationAdapter.this.mInterstitialAd.loadAd();
                }
            }
        });
    }

    private InterstitialAd getNewInterstitial(Context context) {
        String placementId = (String) getContextData().get("tpn_placement_id");
        if (StringUtils.nullOrEmpty(placementId)) {
            if (StringUtils.notNullNorEmpty(this.staticPlacementId)) {
                FyberLogger.m8448d(TAG, "No placement id found in context data, falling back to configs.");
                placementId = this.staticPlacementId;
            } else {
                FyberLogger.m8453w(TAG, InterstitialMediationAdapter.ERROR_NO_PLACEMENT_ID);
                setAdError(InterstitialMediationAdapter.ERROR_NO_PLACEMENT_ID);
            }
        }
        if (!StringUtils.notNullNorEmpty(placementId)) {
            return null;
        }
        InterstitialAd interstitial = new InterstitialAd(context, placementId);
        interstitial.setAdListener(this);
        return interstitial;
    }

    public void onAdClicked(Ad pAd) {
        interstitialClicked();
    }

    public void onLoggingImpression(Ad ad) {
    }

    public void onAdLoaded(Ad pAd) {
        setAdAvailable();
        this.mInterstitialAd = (InterstitialAd) pAd;
    }

    public void onError(Ad ad, AdError pError) {
        if (pError.getErrorCode() == 1001) {
            FyberLogger.m8451i(TAG, "Callback message from Facebook (code " + pError.getErrorCode() + "): " + pError.getErrorMessage());
            setAdNotAvailable();
            return;
        }
        FyberLogger.m8449e(TAG, "Ad error (" + pError.getErrorCode() + "): " + pError.getErrorMessage());
        setAdError("Facebook ad error (" + pError.getErrorCode() + "): " + pError.getErrorMessage());
    }

    public void onInterstitialDismissed(Ad pAd) {
        interstitialClosed();
        this.mInterstitialAd = null;
    }

    public void onInterstitialDisplayed(Ad pAd) {
        interstitialShown();
    }
}
