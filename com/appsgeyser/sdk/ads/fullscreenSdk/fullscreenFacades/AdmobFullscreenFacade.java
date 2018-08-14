package com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades;

import android.content.Context;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdmobFullscreenFacade extends AbstractFullscreenFacade {
    private InterstitialAd interstitialAd;

    class C12491 extends AdListener {
        C12491() {
        }

        public void onAdClosed() {
            super.onAdClosed();
            AdmobFullscreenFacade.this.listener.onFullscreenClosed();
        }

        public void onAdFailedToLoad(int i) {
            super.onAdFailedToLoad(i);
            AdmobFullscreenFacade.this.listener.onFullscreenError("Can not load admob ad. Error code is: " + String.valueOf(i));
        }

        public void onAdLeftApplication() {
            super.onAdLeftApplication();
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_ADMOB_NATIVE_SDK_CLICK, StatController.generateQueryParametersForSdk(AdmobFullscreenFacade.this.configPhp, AdmobFullscreenFacade.this.context, AdsType.FULLSCREEN, "admob fullscreen sdk", StatController.KEY_ADMOB), AdmobFullscreenFacade.this.context, false);
            AdmobFullscreenFacade.this.listener.onFullscreenClicked();
        }

        public void onAdOpened() {
            super.onAdOpened();
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_ADMOB_NATIVE_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(AdmobFullscreenFacade.this.configPhp, AdmobFullscreenFacade.this.context, AdsType.FULLSCREEN, "admob fullscreen sdk", StatController.KEY_ADMOB), AdmobFullscreenFacade.this.context, false);
            AdmobFullscreenFacade.this.listener.onFullscreenOpened();
        }

        public void onAdLoaded() {
            super.onAdLoaded();
            AdmobFullscreenFacade.this.listener.onFullscreenReceived();
        }

        public void onAdClicked() {
            super.onAdClicked();
        }

        public void onAdImpression() {
            super.onAdImpression();
        }
    }

    public AdmobFullscreenFacade(Context context, ConfigPhp configPhp) {
        super(context, configPhp);
    }

    protected void init() {
        MobileAds.initialize(this.context, ((AdNetworkSdkModel) this.configPhp.getFullscreenSdk().get(StatController.KEY_ADMOB)).getAppId());
        this.interstitialAd = new InterstitialAd(this.context);
        this.interstitialAd.setAdUnitId(((AdNetworkSdkModel) this.configPhp.getFullscreenSdk().get(StatController.KEY_ADMOB)).getPlacementId());
        this.interstitialAd.setAdListener(new C12491());
    }

    public void loadFullscreenAd() {
        this.interstitialAd.loadAd(new Builder().build());
    }

    public void showFullscreenAd() {
        this.interstitialAd.show();
    }

    public boolean isFullscreenLoaded() {
        return this.interstitialAd.isLoaded();
    }
}
