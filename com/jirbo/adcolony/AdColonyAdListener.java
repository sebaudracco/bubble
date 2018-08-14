package com.jirbo.adcolony;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyReward;
import com.adcolony.sdk.AdColonyRewardListener;
import com.adcolony.sdk.AdColonyZone;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;

class AdColonyAdListener extends AdColonyInterstitialListener implements AdColonyRewardListener {
    private AdColonyAdapter _adapter;
    private MediationInterstitialListener _mediationInterstitialListener;
    private MediationRewardedVideoAdListener _mediationRewardedVideoAdListener;
    private boolean _rewarded;
    private RequestState _state = RequestState.NONE;

    enum RequestState {
        REQUESTED,
        FILLED,
        NOT_FILLED,
        CLOSED,
        EXPIRED,
        NONE
    }

    AdColonyAdListener(AdColonyAdapter adapter, MediationInterstitialListener listener) {
        this._mediationInterstitialListener = listener;
        this._adapter = adapter;
    }

    AdColonyAdListener(AdColonyAdapter adapter, MediationRewardedVideoAdListener listener) {
        this._mediationRewardedVideoAdListener = listener;
        this._adapter = adapter;
        this._rewarded = true;
    }

    public void onRequestFilled(AdColonyInterstitial ad) {
        if (this._adapter != null) {
            this._state = RequestState.FILLED;
            this._adapter.setAd(ad);
            notifyAdLoaded();
        }
    }

    public void onClicked(AdColonyInterstitial ad) {
        if (this._adapter != null) {
            this._adapter.setAd(ad);
            if (this._rewarded) {
                this._mediationRewardedVideoAdListener.onAdClicked(this._adapter);
            } else {
                this._mediationInterstitialListener.onAdClicked(this._adapter);
            }
        }
    }

    public void onClosed(AdColonyInterstitial ad) {
        if (this._adapter != null) {
            this._state = RequestState.CLOSED;
            this._adapter.setAd(ad);
            if (this._rewarded) {
                this._mediationRewardedVideoAdListener.onAdClosed(this._adapter);
            } else {
                this._mediationInterstitialListener.onAdClosed(this._adapter);
            }
        }
    }

    public void onExpiring(AdColonyInterstitial ad) {
        if (this._adapter != null) {
            this._state = RequestState.EXPIRED;
            this._adapter.setAd(ad);
            AdColony.requestInterstitial(ad.getZoneID(), this);
        }
    }

    public void onIAPEvent(AdColonyInterstitial ad, String productId, int engagementType) {
        if (this._adapter != null) {
            this._adapter.setAd(ad);
        }
    }

    public void onLeftApplication(AdColonyInterstitial ad) {
        if (this._adapter != null) {
            this._adapter.setAd(ad);
            if (this._rewarded) {
                this._mediationRewardedVideoAdListener.onAdLeftApplication(this._adapter);
            } else {
                this._mediationInterstitialListener.onAdLeftApplication(this._adapter);
            }
        }
    }

    public void onOpened(AdColonyInterstitial ad) {
        if (this._adapter != null) {
            this._adapter.setAd(ad);
            if (this._rewarded) {
                this._mediationRewardedVideoAdListener.onAdOpened(this._adapter);
                this._mediationRewardedVideoAdListener.onVideoStarted(this._adapter);
                return;
            }
            this._mediationInterstitialListener.onAdOpened(this._adapter);
        }
    }

    public void onRequestNotFilled(AdColonyZone zone) {
        if (this._adapter != null) {
            this._state = RequestState.NOT_FILLED;
            this._adapter.setAd(null);
            if (this._rewarded) {
                AdColony.removeRewardListener();
                this._mediationRewardedVideoAdListener.onAdFailedToLoad(this._adapter, 3);
                return;
            }
            this._mediationInterstitialListener.onAdFailedToLoad(this._adapter, 3);
        }
    }

    public void onReward(AdColonyReward reward) {
        if (this._adapter != null && reward.success()) {
            this._mediationRewardedVideoAdListener.onRewarded(this._adapter, new AdColonyReward(reward.getRewardName(), reward.getRewardAmount()));
        }
    }

    void destroy() {
        this._adapter = null;
        this._mediationInterstitialListener = null;
        this._mediationRewardedVideoAdListener = null;
    }

    void onRequest() {
        this._state = RequestState.REQUESTED;
    }

    RequestState getState() {
        return this._state;
    }

    void notifyAdLoaded() {
        if (this._rewarded) {
            this._mediationRewardedVideoAdListener.onAdLoaded(this._adapter);
        } else {
            this._mediationInterstitialListener.onAdLoaded(this._adapter);
        }
    }
}
