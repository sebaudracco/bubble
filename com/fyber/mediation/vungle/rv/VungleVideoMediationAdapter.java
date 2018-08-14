package com.fyber.mediation.vungle.rv;

import android.app.Activity;
import android.content.Context;
import com.fyber.ads.videos.mediation.RewardedVideoMediationAdapter;
import com.fyber.ads.videos.mediation.TPNVideoValidationResult;
import com.fyber.mediation.vungle.VungleMediationAdapter;
import com.fyber.utils.FyberLogger;
import com.vungle.publisher.VungleAdEventListener;
import com.vungle.publisher.VunglePub;

public class VungleVideoMediationAdapter extends RewardedVideoMediationAdapter<VungleMediationAdapter> implements VungleAdEventListener {
    private final String TAG = VungleVideoMediationAdapter.class.getSimpleName();
    private VungleMediationAdapter mainAdapter;
    private String rvPlacementId;
    private boolean waitingForAdAvailablityCallback = false;

    public VungleVideoMediationAdapter(VungleMediationAdapter adapter, String configRvPlacementId) {
        super(adapter);
        this.mainAdapter = adapter;
        this.rvPlacementId = configRvPlacementId;
    }

    public void videosAvailable(Context context) {
        VunglePub vunglePub = VunglePub.getInstance();
        if (vunglePub.isAdPlayable(this.rvPlacementId)) {
            sendValidationEvent(TPNVideoValidationResult.Success);
            return;
        }
        this.waitingForAdAvailablityCallback = true;
        vunglePub.loadAd(this.rvPlacementId);
    }

    public void startVideo(Activity parentActivity) {
        VunglePub vunglePub = VunglePub.getInstance();
        if (vunglePub.isAdPlayable(this.rvPlacementId)) {
            vunglePub.playAd(this.rvPlacementId, this.mainAdapter.getConfiguredAdConfig());
            return;
        }
        notifyVideoError();
    }

    public void startPrecaching() {
    }

    public void onAdEnd(String placementReferenceId, boolean wasSuccessFulView, boolean wasCallToActionClicked) {
        if (placementReferenceId.equals(this.rvPlacementId)) {
            if (wasSuccessFulView) {
                setVideoPlayed();
            }
            notifyCloseEngagement();
        }
    }

    public void onAdStart(String placementReferenceId) {
        if (placementReferenceId.equals(this.rvPlacementId)) {
            notifyVideoStarted();
        }
    }

    public void onUnableToPlayAd(String placementReferenceId, String reason) {
        if (placementReferenceId.equals(this.rvPlacementId)) {
            notifyVideoError();
        }
    }

    public void onAdAvailabilityUpdate(String placementReferenceId, boolean isAdAvailable) {
        if (placementReferenceId.equals(this.rvPlacementId) && this.waitingForAdAvailablityCallback) {
            FyberLogger.m8448d(this.TAG, "Ad availability update: " + placementReferenceId + " = " + isAdAvailable);
            if (isAdAvailable) {
                sendValidationEvent(TPNVideoValidationResult.Success);
            } else {
                sendValidationEvent(TPNVideoValidationResult.NoVideoAvailable);
            }
            this.waitingForAdAvailablityCallback = false;
        }
    }
}
