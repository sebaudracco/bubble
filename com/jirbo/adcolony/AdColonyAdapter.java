package com.jirbo.adcolony;

import android.content.Context;
import android.os.Bundle;
import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;

public class AdColonyAdapter implements MediationInterstitialAdapter, MediationRewardedVideoAdAdapter {
    private AdColonyInterstitial _adColonyInterstitial;
    private AdColonyAdListener _adColonyInterstitialListener;
    private AdColonyAdListener _adColonyRewardedInterstitialListener;
    private MediationRewardedVideoAdListener _mediationRewardedVideoAdListener;

    public void onDestroy() {
        AdColonyManager.getInstance().onDestroy();
        if (this._adColonyInterstitial != null) {
            this._adColonyInterstitial.cancel();
            this._adColonyInterstitial.destroy();
        }
        if (this._adColonyInterstitialListener != null) {
            this._adColonyInterstitialListener.destroy();
        }
        if (this._adColonyRewardedInterstitialListener != null) {
            this._adColonyRewardedInterstitialListener.destroy();
            AdColony.removeRewardListener();
        }
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle serverParams, MediationAdRequest mediationAdRequest, Bundle mediationExtras) {
        this._adColonyInterstitialListener = new AdColonyAdListener(this, mediationInterstitialListener);
        boolean success = AdColonyManager.getInstance().configureAdColony(context, serverParams, mediationAdRequest, mediationExtras);
        if (success) {
            String requestedZone = AdColonyManager.getInstance().getZoneFromRequest(AdColonyManager.getInstance().parseZoneList(serverParams), mediationExtras);
            if (requestedZone != null) {
                AdColony.requestInterstitial(requestedZone, this._adColonyInterstitialListener);
            } else {
                success = false;
            }
        }
        if (!success) {
            mediationInterstitialListener.onAdFailedToLoad(this, 1);
        }
    }

    public void showInterstitial() {
        showAdColonyInterstitial();
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String userId, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle serverParams, Bundle networkExtras) {
        this._mediationRewardedVideoAdListener = mediationRewardedVideoAdListener;
        this._adColonyRewardedInterstitialListener = new AdColonyAdListener(this, mediationRewardedVideoAdListener);
        if (AdColonyManager.getInstance().configureAdColony(context, serverParams, mediationAdRequest, networkExtras)) {
            AdColonyManager.getInstance().rewardedAdsConfigured = true;
            this._mediationRewardedVideoAdListener.onInitializationSucceeded(this);
            return;
        }
        this._mediationRewardedVideoAdListener.onInitializationFailed(this, 1);
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle serverParams, Bundle networkExtras) {
        boolean showPrePopup = false;
        boolean showPostPopup = false;
        String requestedZone = AdColonyManager.getInstance().getZoneFromRequest(AdColonyManager.getInstance().parseZoneList(serverParams), networkExtras);
        if (this._adColonyRewardedInterstitialListener.getState() == RequestState.FILLED) {
            this._adColonyRewardedInterstitialListener.notifyAdLoaded();
        } else if (this._adColonyRewardedInterstitialListener.getState() != RequestState.REQUESTED) {
            boolean success = AdColonyManager.getInstance().configureAdColony(null, serverParams, mediationAdRequest, networkExtras);
            if (networkExtras != null) {
                showPrePopup = networkExtras.getBoolean("show_pre_popup", false);
                showPostPopup = networkExtras.getBoolean("show_post_popup", false);
            }
            if (this._adColonyRewardedInterstitialListener == null) {
                this._adColonyRewardedInterstitialListener = new AdColonyAdListener(this, this._mediationRewardedVideoAdListener);
            }
            if (AdColony.getRewardListener() == null) {
                AdColony.setRewardListener(this._adColonyRewardedInterstitialListener);
            }
            if (requestedZone != null) {
                AdColonyAdOptions adOptions = new AdColonyAdOptions().enableConfirmationDialog(showPrePopup).enableResultsDialog(showPostPopup);
                this._adColonyRewardedInterstitialListener.onRequest();
                AdColony.requestInterstitial(requestedZone, this._adColonyRewardedInterstitialListener, adOptions);
            } else {
                success = false;
            }
            if (!success) {
                this._mediationRewardedVideoAdListener.onAdFailedToLoad(this, 1);
            }
        }
    }

    public void showVideo() {
        showAdColonyInterstitial();
    }

    public boolean isInitialized() {
        return AdColonyManager.getInstance().rewardedAdsConfigured;
    }

    private void showAdColonyInterstitial() {
        if (this._adColonyInterstitial != null) {
            this._adColonyInterstitial.show();
        }
    }

    void setAd(AdColonyInterstitial interstitialAd) {
        this._adColonyInterstitial = interstitialAd;
    }
}
