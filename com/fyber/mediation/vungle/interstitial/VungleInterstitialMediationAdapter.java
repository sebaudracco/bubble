package com.fyber.mediation.vungle.interstitial;

import android.app.Activity;
import android.content.Context;
import com.fyber.ads.interstitials.mediation.InterstitialMediationAdapter;
import com.fyber.mediation.vungle.VungleMediationAdapter;
import com.fyber.utils.FyberLogger;
import com.vungle.publisher.VungleAdEventListener;
import com.vungle.publisher.VunglePub;

public class VungleInterstitialMediationAdapter extends InterstitialMediationAdapter<VungleMediationAdapter> implements VungleAdEventListener {
    private final String TAG = VungleInterstitialMediationAdapter.class.getSimpleName();
    private String intPlacementId;
    private VungleMediationAdapter mainAdapter;
    private boolean waitingForAdAvailablityCallback = false;

    public VungleInterstitialMediationAdapter(VungleMediationAdapter adapter, String configIntPlacementId) {
        super(adapter);
        this.mainAdapter = adapter;
        this.intPlacementId = configIntPlacementId;
    }

    protected void show(Activity parentActivity) {
        VunglePub vunglePub = VunglePub.getInstance();
        if (vunglePub.isAdPlayable(this.intPlacementId)) {
            vunglePub.playAd(this.intPlacementId, this.mainAdapter.getConfiguredAdConfig());
            return;
        }
        interstitialError("Interstitial is not ready");
    }

    protected void checkForAds(Context context) {
        VunglePub vunglePub = VunglePub.getInstance();
        if (vunglePub.isAdPlayable(this.intPlacementId)) {
            setAdAvailable();
            return;
        }
        this.waitingForAdAvailablityCallback = true;
        vunglePub.loadAd(this.intPlacementId);
    }

    public void onAdEnd(String placementReferenceId, boolean wasSuccessFulView, boolean wasCallToActionClicked) {
        if (placementReferenceId.equals(this.intPlacementId)) {
            if (wasCallToActionClicked) {
                interstitialClicked();
            }
            interstitialClosed();
        }
    }

    public void onAdStart(String placementReferenceId) {
        if (placementReferenceId.equals(this.intPlacementId)) {
            interstitialShown();
        }
    }

    public void onUnableToPlayAd(String placementReferenceId, String reason) {
        if (placementReferenceId.equals(this.intPlacementId)) {
            interstitialError(reason);
        }
    }

    public void onAdAvailabilityUpdate(String placementReferenceId, boolean isAdAvailable) {
        if (placementReferenceId.equals(this.intPlacementId) && this.waitingForAdAvailablityCallback) {
            FyberLogger.m8448d(this.TAG, "Ad availability update: " + placementReferenceId + " = " + isAdAvailable);
            if (isAdAvailable) {
                setAdAvailable();
            } else {
                setAdNotAvailable();
            }
            this.waitingForAdAvailablityCallback = false;
        }
    }
}
