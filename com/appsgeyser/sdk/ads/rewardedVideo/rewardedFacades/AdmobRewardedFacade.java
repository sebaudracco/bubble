package com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades;

import android.content.Context;
import android.text.TextUtils;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.mopub.common.AdType;

public class AdmobRewardedFacade extends AbstractRewardedFacade {
    private final String APPSGEYSER_PLACEMENT = "appsgeyser";
    private final String CUSTOM_PLACEMENT = AdType.CUSTOM;
    private String previouslyLoadedPlacementId = AdType.CUSTOM;
    private RewardedVideoAd rewardedVideoAd;

    class C12771 implements RewardedVideoAdListener {
        C12771() {
        }

        public void onRewardedVideoAdLoaded() {
            AdmobRewardedFacade.this.listener.onVideoLoaded();
        }

        public void onRewardedVideoAdOpened() {
            AdmobRewardedFacade.this.listener.onVideoOpened();
        }

        public void onRewardedVideoStarted() {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_ADMOB_REWARDED_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(AdmobRewardedFacade.this.configPhp, AdmobRewardedFacade.this.context, AdsType.REWARDED, "admob rewarded video impression", StatController.KEY_ADMOB), AdmobRewardedFacade.this.context, false);
        }

        public void onRewardedVideoAdClosed() {
            AdmobRewardedFacade.this.listener.onVideoClosed();
        }

        public void onRewarded(RewardItem rewardItem) {
            AdmobRewardedFacade.this.listener.onVideoFinished();
        }

        public void onRewardedVideoAdLeftApplication() {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_ADMOB_REWARDED_SDK_CLICK, StatController.generateQueryParametersForSdk(AdmobRewardedFacade.this.configPhp, AdmobRewardedFacade.this.context, AdsType.REWARDED, "admob rewarded video click", StatController.KEY_ADMOB), AdmobRewardedFacade.this.context, false);
            AdmobRewardedFacade.this.listener.onVideoClicked();
        }

        public void onRewardedVideoAdFailedToLoad(int i) {
            AdmobRewardedFacade.this.listener.onVideoError("admob error");
        }

        public void onRewardedVideoCompleted() {
        }
    }

    public AdmobRewardedFacade(Context context, ConfigPhp configPhp) {
        super(context, configPhp);
    }

    protected void setPriority() {
        this.priority = ((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_ADMOB)).getPriority();
    }

    protected void init() {
        MobileAds.initialize(this.context, ((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_ADMOB)).getAppId());
        this.rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this.context);
    }

    public void loadRewardedVideo() {
        this.rewardedVideoAd.setRewardedVideoAdListener(new C12771());
        this.rewardedVideoAd.loadAd(defineWhichPlacementToUse(), new Builder().build());
    }

    private String defineWhichPlacementToUse() {
        AdNetworkSdkModel sdk = (AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_ADMOB);
        if (TextUtils.isEmpty(sdk.getCustomPlacementId())) {
            return sdk.getPlacementId();
        }
        if (this.previouslyLoadedPlacementId.equals(AdType.CUSTOM)) {
            this.previouslyLoadedPlacementId = "appsgeyser";
            return sdk.getPlacementId();
        }
        this.previouslyLoadedPlacementId = AdType.CUSTOM;
        return sdk.getCustomPlacementId();
    }

    public boolean isVideoLoaded() {
        return this.rewardedVideoAd.isLoaded();
    }

    public void showRewardedVideo() {
        this.rewardedVideoAd.show();
    }

    public void onPause() {
        if (this.rewardedVideoAd != null) {
            this.rewardedVideoAd.pause(this.context);
        }
    }

    public void onResume() {
        if (this.rewardedVideoAd != null) {
            this.rewardedVideoAd.resume(this.context);
        }
    }

    public void onDestroy() {
        if (this.rewardedVideoAd != null) {
            this.rewardedVideoAd.destroy(this.context);
        }
    }
}
