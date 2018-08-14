package com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.VungleAdEventListener;
import com.vungle.publisher.VungleInitListener;
import com.vungle.publisher.VunglePub;

public class VungleRewardedFacade extends AbstractRewardedFacade implements VungleAdEventListener {
    private AdConfig globalAdConfig;
    private Handler loadingTimeoutHandler;
    private Runnable loadingTimeoutRunnable;
    private VunglePub vunglePub;

    class C12861 implements VungleInitListener {
        C12861() {
        }

        public void onSuccess() {
            Log.d("vungleSDK", "vungle init succeed");
            VungleRewardedFacade.this.globalAdConfig = VungleRewardedFacade.this.vunglePub.getGlobalAdConfig();
            if (VungleRewardedFacade.this.globalAdConfig != null) {
                VungleRewardedFacade.this.globalAdConfig.setSoundEnabled(true);
            }
        }

        public void onFailure(Throwable throwable) {
            Log.d("vungleSDK", "vungle init failed");
        }
    }

    public VungleRewardedFacade(Context context, ConfigPhp configPhp) {
        super(context, configPhp);
    }

    protected void setPriority() {
        this.priority = ((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_VUNGLE)).getPriority();
    }

    protected void init() {
        this.vunglePub = VunglePub.getInstance();
        this.vunglePub.init(this.context, ((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_VUNGLE)).getAppId(), new String[]{((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_VUNGLE)).getPlacementId()}, new C12861());
        this.loadingTimeoutHandler = new Handler();
        this.loadingTimeoutRunnable = VungleRewardedFacade$$Lambda$1.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$init$0(VungleRewardedFacade this_) {
        Log.d("vungleSDK", "timer fired");
        this_.listener.onVideoError("Video loading too long");
        this_.vunglePub.clearEventListeners();
    }

    public void loadRewardedVideo() {
        this.vunglePub.clearAndSetEventListeners(new VungleAdEventListener[]{this});
        if (this.vunglePub.isInitialized()) {
            this.loadingTimeoutHandler.removeCallbacks(this.loadingTimeoutRunnable);
            this.loadingTimeoutHandler.postDelayed(this.loadingTimeoutRunnable, (long) (((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_VUNGLE)).getLoadingTimeout() * 1000));
            return;
        }
        this.listener.onVideoError("vungle not inited");
        this.vunglePub.clearEventListeners();
    }

    public boolean isVideoLoaded() {
        return this.vunglePub.isAdPlayable(((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_VUNGLE)).getPlacementId());
    }

    public void showRewardedVideo() {
        this.vunglePub.playAd(((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_VUNGLE)).getPlacementId(), this.globalAdConfig);
    }

    public void onPause() {
        if (this.vunglePub != null) {
            this.vunglePub.onPause();
        }
    }

    public void onResume() {
        if (this.vunglePub != null) {
            this.vunglePub.onResume();
        }
    }

    public void onAdEnd(@NonNull String placementReferenceId, boolean wasSuccessfulView, boolean wasCallToActionClicked) {
        if (wasSuccessfulView) {
            this.listener.onVideoFinished();
        }
        if (wasCallToActionClicked) {
            this.listener.onVideoClicked();
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_VUNGLE_REWARDED_SDK_CLICK, StatController.generateQueryParametersForSdk(this.configPhp, this.context, AdsType.REWARDED, "vungle rewarded video click", StatController.KEY_VUNGLE), this.context, false);
        }
        this.listener.onVideoClosed();
        Log.d(StatController.KEY_VUNGLE, "onAdEnd");
    }

    public void onAdStart(@NonNull String placementReferenceId) {
        this.listener.onVideoOpened();
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_VUNGLE_REWARDED_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(this.configPhp, this.context, AdsType.REWARDED, "vungle rewarded video impression", StatController.KEY_VUNGLE), this.context, false);
        Log.d(StatController.KEY_VUNGLE, "onAdStart");
    }

    public void onUnableToPlayAd(@NonNull String placementReferenceId, String reason) {
        this.listener.onVideoError(reason);
        Log.d(StatController.KEY_VUNGLE, "onUnableToPlayAd");
    }

    public void onAdAvailabilityUpdate(@NonNull String placementReferenceId, boolean isAdAvailable) {
        if (isAdAvailable) {
            this.listener.onVideoLoaded();
            this.loadingTimeoutHandler.removeCallbacks(this.loadingTimeoutRunnable);
        }
        Log.d(StatController.KEY_VUNGLE, "onAdAvailabilityUpdate");
    }
}
