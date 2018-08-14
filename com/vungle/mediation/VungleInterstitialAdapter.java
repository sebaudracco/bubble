package com.vungle.mediation;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.vungle.publisher.AdConfig;

public class VungleInterstitialAdapter implements MediationInterstitialAdapter {
    private static final String TAG = VungleManager.class.getSimpleName();
    private static int sCounter = 0;
    private AdConfig mAdConfig;
    private String mAdapterId;
    private final String mId = "interstitial";
    private MediationInterstitialListener mMediationInterstitialListener;
    private String mPlacementForPlay;
    private final VungleListener mVungleListener = new C41541();
    private VungleManager mVungleManager;

    class C41541 extends VungleListener {
        C41541() {
        }

        public void onAdEnd(String placement, boolean wasSuccessfulView, boolean wasCallToActionClicked) {
            if (VungleInterstitialAdapter.this.mMediationInterstitialListener != null) {
                VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdClosed(VungleInterstitialAdapter.this);
            }
        }

        public void onAdStart(String placement) {
            if (VungleInterstitialAdapter.this.mMediationInterstitialListener != null) {
                VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdOpened(VungleInterstitialAdapter.this);
            }
        }

        public void onAdAvailable() {
            if (VungleInterstitialAdapter.this.mMediationInterstitialListener != null) {
                VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdLoaded(VungleInterstitialAdapter.this);
            }
        }

        void onInitialized(boolean isSuccess) {
            if (VungleInterstitialAdapter.this.mMediationInterstitialListener == null) {
                return;
            }
            if (isSuccess) {
                VungleInterstitialAdapter.this.loadAd();
            } else {
                VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdFailedToLoad(VungleInterstitialAdapter.this, 0);
            }
        }

        void onAdFail(String placement) {
            if (placement.equals(VungleInterstitialAdapter.this.mPlacementForPlay) && VungleInterstitialAdapter.this.mMediationInterstitialListener != null) {
                VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdClosed(VungleInterstitialAdapter.this);
            }
        }
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle serverParameters, MediationAdRequest mediationAdRequest, Bundle mediationExtras) {
        try {
            Config config = AdapterParametersParser.parse(mediationExtras, serverParameters);
            this.mMediationInterstitialListener = mediationInterstitialListener;
            this.mVungleManager = VungleManager.getInstance(config.getAppId(), config.getAllPlacements());
            this.mPlacementForPlay = this.mVungleManager.findPlacement(mediationExtras, serverParameters);
            this.mAdConfig = VungleExtrasBuilder.adConfigWithNetworkExtras(mediationExtras);
            this.mAdapterId = "interstitial" + String.valueOf(sCounter);
            sCounter++;
            this.mVungleManager.addListener(this.mAdapterId, this.mVungleListener);
            if (this.mVungleManager.isInitialized()) {
                loadAd();
                return;
            }
            this.mVungleListener.setWaitingInit(true);
            this.mVungleManager.init(context);
        } catch (IllegalArgumentException e) {
            if (mediationInterstitialListener != null) {
                mediationInterstitialListener.onAdFailedToLoad(this, 1);
            }
        }
    }

    private void loadAd() {
        if (!this.mVungleManager.isAdPlayable(this.mPlacementForPlay)) {
            this.mVungleListener.waitForAd(this.mPlacementForPlay);
            this.mVungleManager.loadAd(this.mPlacementForPlay);
        } else if (this.mMediationInterstitialListener != null) {
            this.mMediationInterstitialListener.onAdLoaded(this);
        }
    }

    public void showInterstitial() {
        if (this.mVungleManager != null) {
            this.mVungleManager.playAd(this.mPlacementForPlay, this.mAdConfig, this.mAdapterId);
        }
    }

    public void onDestroy() {
        if (this.mVungleManager != null) {
            this.mVungleManager.removeListener(this.mAdapterId);
        }
    }

    public void onPause() {
        if (this.mVungleManager != null) {
            this.mVungleManager.onPause();
        }
    }

    public void onResume() {
        if (this.mVungleManager != null) {
            this.mVungleManager.onResume();
        }
    }
}
