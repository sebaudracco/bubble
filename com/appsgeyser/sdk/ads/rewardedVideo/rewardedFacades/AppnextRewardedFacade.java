package com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades;

import android.content.Context;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.base.Appnext;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.appnext.core.callbacks.OnVideoEnded;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;

public class AppnextRewardedFacade extends AbstractRewardedFacade {
    private RewardedVideo rewardedVideo;

    class C12781 implements OnAdLoaded {
        C12781() {
        }

        public void adLoaded(String bannerId) {
            AppnextRewardedFacade.this.listener.onVideoLoaded();
        }
    }

    class C12792 implements OnAdOpened {
        C12792() {
        }

        public void adOpened() {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_APPNEXT_REWARDED_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(AppnextRewardedFacade.this.configPhp, AppnextRewardedFacade.this.context, AdsType.REWARDED, "appnext rewarded video impression", StatController.KEY_APPNEXT), AppnextRewardedFacade.this.context, false);
            AppnextRewardedFacade.this.listener.onVideoOpened();
        }
    }

    class C12803 implements OnAdClicked {
        C12803() {
        }

        public void adClicked() {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_APPNEXT_REWARDED_SDK_CLICK, StatController.generateQueryParametersForSdk(AppnextRewardedFacade.this.configPhp, AppnextRewardedFacade.this.context, AdsType.REWARDED, "appnext rewarded video click", StatController.KEY_APPNEXT), AppnextRewardedFacade.this.context, false);
            AppnextRewardedFacade.this.listener.onVideoClicked();
        }
    }

    class C12814 implements OnAdClosed {
        C12814() {
        }

        public void onAdClosed() {
            AppnextRewardedFacade.this.rewardedVideo = null;
            AppnextRewardedFacade.this.listener.onVideoClosed();
        }
    }

    class C12825 implements OnAdError {
        C12825() {
        }

        public void adError(String errorMessage) {
            AppnextRewardedFacade.this.rewardedVideo = null;
            AppnextRewardedFacade.this.listener.onVideoError(errorMessage);
        }
    }

    class C12836 implements OnVideoEnded {
        C12836() {
        }

        public void videoEnded() {
            AppnextRewardedFacade.this.listener.onVideoFinished();
        }
    }

    public AppnextRewardedFacade(Context context, ConfigPhp configPhp) {
        super(context, configPhp);
    }

    protected void setPriority() {
        this.priority = ((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_APPNEXT)).getPriority();
    }

    protected void init() {
        Appnext.init(this.context);
    }

    public void loadRewardedVideo() {
        this.rewardedVideo = new RewardedVideo(this.context, ((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_APPNEXT)).getPlacementId());
        setupCallbacks();
        this.rewardedVideo.loadAd();
    }

    private void setupCallbacks() {
        this.rewardedVideo.setOnAdLoadedCallback(new C12781());
        this.rewardedVideo.setOnAdOpenedCallback(new C12792());
        this.rewardedVideo.setOnAdClickedCallback(new C12803());
        this.rewardedVideo.setOnAdClosedCallback(new C12814());
        this.rewardedVideo.setOnAdErrorCallback(new C12825());
        this.rewardedVideo.setOnVideoEndedCallback(new C12836());
    }

    public boolean isVideoLoaded() {
        return this.rewardedVideo != null && this.rewardedVideo.isAdLoaded();
    }

    public void showRewardedVideo() {
        this.rewardedVideo.showAd();
    }
}
