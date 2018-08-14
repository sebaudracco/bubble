package com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades;

import android.content.Context;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.ads.InMobiInterstitial.InterstitialAdListener2;
import com.inmobi.sdk.InMobiSdk;
import com.inmobi.sdk.InMobiSdk.LogLevel;
import java.util.Map;

public class InmobiRewardedFacade extends AbstractRewardedFacade {
    private InMobiInterstitial inMobiInterstitial;

    class C12851 implements InterstitialAdListener2 {
        C12851() {
        }

        public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
            InmobiRewardedFacade.this.listener.onVideoError(inMobiAdRequestStatus.getMessage());
        }

        public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
        }

        public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
            InmobiRewardedFacade.this.listener.onVideoLoaded();
        }

        public void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
            InmobiRewardedFacade.this.listener.onVideoFinished();
        }

        public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
            InmobiRewardedFacade.this.listener.onVideoError("Can't display inmobi video");
        }

        public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
        }

        public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
        }

        public void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
            InmobiRewardedFacade.this.listener.onVideoClicked();
        }

        public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
            InmobiRewardedFacade.this.listener.onVideoClosed();
        }

        public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
        }
    }

    public InmobiRewardedFacade(Context context, ConfigPhp configPhp) {
        super(context, configPhp);
    }

    protected void setPriority() {
        this.priority = ((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_INMOBI)).getPriority();
    }

    protected void init() {
        InMobiSdk.init(this.context, ((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_INMOBI)).getAppId());
        InMobiSdk.setLogLevel(LogLevel.DEBUG);
        this.inMobiInterstitial = new InMobiInterstitial(this.context, Long.parseLong(((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_INMOBI)).getPlacementId()), new C12851());
    }

    public void loadRewardedVideo() {
        this.inMobiInterstitial.load();
    }

    public boolean isVideoLoaded() {
        return this.inMobiInterstitial.isReady();
    }

    public void showRewardedVideo() {
        this.inMobiInterstitial.show();
    }
}
