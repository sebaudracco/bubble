package com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades;

import android.content.Context;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.ads.InMobiInterstitial.InterstitialAdListener2;
import com.inmobi.sdk.InMobiSdk;
import java.util.Map;

public class InmobiFullscreenFacade extends AbstractFullscreenFacade {
    private InMobiInterstitial inMobiInterstitial;

    class C12571 implements InterstitialAdListener2 {
        C12571() {
        }

        public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
            InmobiFullscreenFacade.this.listener.onFullscreenError(inMobiAdRequestStatus.getMessage());
        }

        public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
        }

        public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
            InmobiFullscreenFacade.this.listener.onFullscreenReceived();
        }

        public void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
        }

        public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
            InmobiFullscreenFacade.this.listener.onFullscreenError("Cant display interstitial video from INMOBI");
        }

        public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
        }

        public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INMOBI_NATIVE_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(InmobiFullscreenFacade.this.configPhp, InmobiFullscreenFacade.this.context, AdsType.FULLSCREEN, "inmobi fullscreen sdk", StatController.KEY_INMOBI), InmobiFullscreenFacade.this.context, false);
            InmobiFullscreenFacade.this.listener.onFullscreenOpened();
        }

        public void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INMOBI_NATIVE_SDK_CLICK, StatController.generateQueryParametersForSdk(InmobiFullscreenFacade.this.configPhp, InmobiFullscreenFacade.this.context, AdsType.FULLSCREEN, "inmobi fullscreen sdk", StatController.KEY_INMOBI), InmobiFullscreenFacade.this.context, false);
            InmobiFullscreenFacade.this.listener.onFullscreenClicked();
        }

        public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
            InmobiFullscreenFacade.this.listener.onFullscreenClosed();
        }

        public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
            InmobiFullscreenFacade.this.listener.onFullscreenClosed();
        }
    }

    public InmobiFullscreenFacade(Context context, ConfigPhp configPhp) {
        super(context, configPhp);
    }

    protected void init() {
        InMobiSdk.init(this.context, ((AdNetworkSdkModel) this.configPhp.getFullscreenSdk().get(StatController.KEY_INMOBI)).getAppId());
        this.inMobiInterstitial = new InMobiInterstitial(this.context, Long.parseLong(((AdNetworkSdkModel) this.configPhp.getFullscreenSdk().get(StatController.KEY_INMOBI)).getPlacementId()), new C12571());
    }

    public void loadFullscreenAd() {
        this.inMobiInterstitial.load();
    }

    public void showFullscreenAd() {
        this.inMobiInterstitial.show();
    }

    public boolean isFullscreenLoaded() {
        return this.inMobiInterstitial.isReady();
    }
}
