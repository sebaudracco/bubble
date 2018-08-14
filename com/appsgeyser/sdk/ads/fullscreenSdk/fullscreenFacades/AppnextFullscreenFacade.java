package com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades;

import android.content.Context;
import com.appnext.ads.interstitial.Interstitial;
import com.appnext.base.Appnext;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;

public class AppnextFullscreenFacade extends AbstractFullscreenFacade {
    private Interstitial interstitialAd;

    class C12501 implements OnAdError {
        C12501() {
        }

        public void adError(String s) {
            AppnextFullscreenFacade.this.listener.onFullscreenError(s);
        }
    }

    class C12512 implements OnAdOpened {
        C12512() {
        }

        public void adOpened() {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_APPNEXT_NATIVE_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(AppnextFullscreenFacade.this.configPhp, AppnextFullscreenFacade.this.context, AdsType.FULLSCREEN, "appnext fullscreen sdk", StatController.KEY_APPNEXT), AppnextFullscreenFacade.this.context, false);
            AppnextFullscreenFacade.this.listener.onFullscreenOpened();
        }
    }

    class C12523 implements OnAdClicked {
        C12523() {
        }

        public void adClicked() {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_APPNEXT_NATIVE_SDK_CLICK, StatController.generateQueryParametersForSdk(AppnextFullscreenFacade.this.configPhp, AppnextFullscreenFacade.this.context, AdsType.FULLSCREEN, "appnext fullscreen sdk", StatController.KEY_APPNEXT), AppnextFullscreenFacade.this.context, false);
            AppnextFullscreenFacade.this.listener.onFullscreenClicked();
        }
    }

    class C12534 implements OnAdLoaded {
        C12534() {
        }

        public void adLoaded(String s) {
            AppnextFullscreenFacade.this.listener.onFullscreenReceived();
        }
    }

    class C12545 implements OnAdClosed {
        C12545() {
        }

        public void onAdClosed() {
            AppnextFullscreenFacade.this.listener.onFullscreenClosed();
        }
    }

    public AppnextFullscreenFacade(Context context, ConfigPhp configPhp) {
        super(context, configPhp);
    }

    protected void init() {
        Appnext.init(this.context);
        this.interstitialAd = new Interstitial(this.context, ((AdNetworkSdkModel) this.configPhp.getFullscreenSdk().get(StatController.KEY_APPNEXT)).getPlacementId());
        this.interstitialAd.setOnAdErrorCallback(new C12501());
        this.interstitialAd.setOnAdOpenedCallback(new C12512());
        this.interstitialAd.setOnAdClickedCallback(new C12523());
        this.interstitialAd.setOnAdLoadedCallback(new C12534());
        this.interstitialAd.setOnAdClosedCallback(new C12545());
    }

    public void loadFullscreenAd() {
        this.interstitialAd.loadAd();
    }

    public void showFullscreenAd() {
        this.interstitialAd.showAd();
    }

    public boolean isFullscreenLoaded() {
        return this.interstitialAd.isAdLoaded();
    }
}
