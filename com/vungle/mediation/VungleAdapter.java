package com.vungle.mediation;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.vungle.publisher.AdConfig;

public class VungleAdapter implements MediationRewardedVideoAdAdapter {
    private static final String TAG = VungleManager.class.getSimpleName();
    private AdConfig mAdConfig;
    private final String mId = "rewardBased";
    private boolean mInitialized;
    private MediationRewardedVideoAdListener mMediationRewardedVideoAdListener;
    private String mPlacementForPlay;
    private final VungleListener mVungleListener = new C41531();
    private VungleManager mVungleManager;

    class C41531 extends VungleListener {
        C41531() {
        }

        public void onAdEnd(String placement, boolean wasSuccessfulView, boolean wasCallToActionClicked) {
            if (VungleAdapter.this.mMediationRewardedVideoAdListener != null) {
                if (wasSuccessfulView) {
                    VungleAdapter.this.mMediationRewardedVideoAdListener.onRewarded(VungleAdapter.this, new VungleReward("vungle", 1));
                }
                VungleAdapter.this.mMediationRewardedVideoAdListener.onAdClosed(VungleAdapter.this);
            }
        }

        public void onAdStart(String placement) {
            if (VungleAdapter.this.mMediationRewardedVideoAdListener != null) {
                VungleAdapter.this.mMediationRewardedVideoAdListener.onAdOpened(VungleAdapter.this);
                VungleAdapter.this.mMediationRewardedVideoAdListener.onVideoStarted(VungleAdapter.this);
            }
        }

        public void onAdAvailable() {
            if (VungleAdapter.this.mMediationRewardedVideoAdListener != null) {
                VungleAdapter.this.mMediationRewardedVideoAdListener.onAdLoaded(VungleAdapter.this);
            }
        }

        void onInitialized(boolean isSuccess) {
            if (VungleAdapter.this.mMediationRewardedVideoAdListener == null) {
                return;
            }
            if (isSuccess) {
                VungleAdapter.this.mInitialized = true;
                VungleAdapter.this.mMediationRewardedVideoAdListener.onInitializationSucceeded(VungleAdapter.this);
                return;
            }
            VungleAdapter.this.mInitialized = false;
            VungleAdapter.this.mMediationRewardedVideoAdListener.onInitializationFailed(VungleAdapter.this, 0);
        }

        void onAdFail(String placement) {
            if (placement.equals(VungleAdapter.this.mPlacementForPlay) && VungleAdapter.this.mMediationRewardedVideoAdListener != null) {
                VungleAdapter.this.mMediationRewardedVideoAdListener.onAdClosed(VungleAdapter.this);
            }
        }
    }

    private class VungleReward implements RewardItem {
        private final int mAmount;
        private final String mType;

        public VungleReward(String type, int amount) {
            this.mType = type;
            this.mAmount = amount;
        }

        public int getAmount() {
            return this.mAmount;
        }

        public String getType() {
            return this.mType;
        }
    }

    public void onDestroy() {
        if (this.mVungleManager != null) {
            this.mVungleManager.removeListener("rewardBased");
        }
        this.mInitialized = false;
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

    public void initialize(Context context, MediationAdRequest adRequest, String unused, MediationRewardedVideoAdListener listener, Bundle serverParameters, Bundle networkExtras) {
        try {
            Config config = AdapterParametersParser.parse(networkExtras, serverParameters);
            this.mMediationRewardedVideoAdListener = listener;
            this.mVungleManager = VungleManager.getInstance(config.getAppId(), config.getAllPlacements());
            this.mVungleManager.addListener("rewardBased", this.mVungleListener);
            if (this.mVungleManager.isInitialized()) {
                this.mInitialized = true;
                this.mMediationRewardedVideoAdListener.onInitializationSucceeded(this);
                return;
            }
            this.mVungleListener.setWaitingInit(true);
            this.mVungleManager.init(context);
        } catch (IllegalArgumentException e) {
            if (listener != null) {
                listener.onInitializationFailed(this, 1);
            }
        }
    }

    public boolean isInitialized() {
        return this.mInitialized;
    }

    public void loadAd(MediationAdRequest adRequest, Bundle serverParameters, Bundle networkExtras) {
        this.mAdConfig = VungleExtrasBuilder.adConfigWithNetworkExtras(networkExtras);
        this.mPlacementForPlay = this.mVungleManager.findPlacement(networkExtras, serverParameters);
        if (!this.mVungleManager.isAdPlayable(this.mPlacementForPlay)) {
            this.mVungleListener.waitForAd(this.mPlacementForPlay);
            this.mVungleManager.loadAd(this.mPlacementForPlay);
        } else if (this.mMediationRewardedVideoAdListener != null) {
            this.mMediationRewardedVideoAdListener.onAdLoaded(this);
        }
    }

    public void showVideo() {
        this.mVungleManager.playAd(this.mPlacementForPlay, this.mAdConfig, "rewardBased");
    }
}
