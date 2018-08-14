package com.fyber.mediation.admob.interstitial;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.fyber.ads.interstitials.mediation.InterstitialMediationAdapter;
import com.fyber.mediation.admob.AdMobMediationAdapter;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

public class AdMobInterstitialMediationAdapter extends InterstitialMediationAdapter<AdMobMediationAdapter> {
    private static final String TAG = AdMobInterstitialMediationAdapter.class.getSimpleName();
    private final String adUnitId;
    private AdMobMediationAdapter adapter;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private InterstitialAd interstitialAd;

    private class AdMobAdListener extends AdListener {
        private AdMobAdListener() {
        }

        public void onAdClosed() {
            super.onAdClosed();
            FyberLogger.m8451i(AdMobInterstitialMediationAdapter.TAG, "Ad closed.");
            AdMobInterstitialMediationAdapter.this.interstitialClosed();
        }

        public void onAdFailedToLoad(int errorCode) {
            switch (errorCode) {
                case 0:
                    AdMobInterstitialMediationAdapter.this.setAdError("Error: internal error");
                    return;
                case 1:
                    AdMobInterstitialMediationAdapter.this.setAdError("Error: invalid request");
                    return;
                case 2:
                    AdMobInterstitialMediationAdapter.this.setAdError("Error: network error");
                    return;
                case 3:
                    AdMobInterstitialMediationAdapter.this.setAdNotAvailable();
                    return;
                default:
                    AdMobInterstitialMediationAdapter.this.setAdError("Error: unknown error");
                    return;
            }
        }

        public void onAdLeftApplication() {
            super.onAdLeftApplication();
            AdMobInterstitialMediationAdapter.this.interstitialClicked();
            FyberLogger.m8451i(AdMobInterstitialMediationAdapter.TAG, "User leaves the application. Clicked on the ad.");
        }

        public void onAdLoaded() {
            super.onAdLoaded();
            AdMobInterstitialMediationAdapter.this.setAdAvailable();
            FyberLogger.m8451i(AdMobInterstitialMediationAdapter.TAG, "Ad received.");
        }

        public void onAdOpened() {
            super.onAdOpened();
            AdMobInterstitialMediationAdapter.this.interstitialShown();
            FyberLogger.m8451i(AdMobInterstitialMediationAdapter.TAG, "Ad opened.");
        }
    }

    public AdMobInterstitialMediationAdapter(AdMobMediationAdapter adapter, String intId) {
        super(adapter);
        this.adapter = adapter;
        this.adUnitId = intId;
    }

    private void postInterstitialLoading(final Context context) {
        Runnable loadInterstitialRunnable = new Runnable() {
            public final void run() {
                AdMobInterstitialMediationAdapter.this.interstitialAd = AdMobInterstitialMediationAdapter.this.getNewInterstitial(context);
                if (AdMobInterstitialMediationAdapter.this.interstitialAd != null) {
                    AdMobInterstitialMediationAdapter.this.interstitialAd.loadAd(AdMobInterstitialMediationAdapter.this.adapter.getRequestBuildHelper().generateRequest());
                    FyberLogger.m8451i(AdMobInterstitialMediationAdapter.TAG, "Loading the ad.");
                }
            }
        };
        this.handler.removeCallbacks(loadInterstitialRunnable, null);
        this.handler.post(loadInterstitialRunnable);
    }

    private InterstitialAd getNewInterstitial(Context context) {
        InterstitialAd interstitial = new InterstitialAd(context);
        String placementId = (String) getContextData().get("tpn_placement_id");
        if (StringUtils.nullOrEmpty(placementId)) {
            if (StringUtils.notNullNorEmpty(this.adUnitId)) {
                FyberLogger.m8448d(TAG, "No placement id found in context data, falling back to configs.");
                placementId = this.adUnitId;
            } else {
                FyberLogger.m8453w(TAG, InterstitialMediationAdapter.ERROR_NO_PLACEMENT_ID);
                setAdError(InterstitialMediationAdapter.ERROR_NO_PLACEMENT_ID);
                return null;
            }
        }
        interstitial.setAdUnitId(placementId);
        interstitial.setAdListener(new AdMobAdListener());
        return interstitial;
    }

    protected void show(Activity parentActivity) {
        if (this.interstitialAd == null || !this.interstitialAd.isLoaded()) {
            interstitialError("Ad was not loaded.");
        } else {
            this.interstitialAd.show();
        }
    }

    protected void checkForAds(Context context) {
        if (this.interstitialAd == null || !this.interstitialAd.isLoading()) {
            postInterstitialLoading(context);
        }
    }
}
