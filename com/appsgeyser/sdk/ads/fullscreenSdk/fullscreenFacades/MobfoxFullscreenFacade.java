package com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades;

import android.content.Context;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;
import com.mobfox.sdk.interstitialads.InterstitialAd;
import com.mobfox.sdk.interstitialads.InterstitialAdListener;

public class MobfoxFullscreenFacade extends AbstractFullscreenFacade {
    private InterstitialAd interstitialAd;

    class C12581 implements InterstitialAdListener {
        C12581() {
        }

        public void onInterstitialLoaded(InterstitialAd interstitialAd) {
            MobfoxFullscreenFacade.this.listener.onFullscreenReceived();
        }

        public void onInterstitialFailed(InterstitialAd interstitialAd, Exception e) {
            MobfoxFullscreenFacade.this.listener.onFullscreenError(e.getMessage());
        }

        public void onInterstitialClosed(InterstitialAd interstitialAd) {
            MobfoxFullscreenFacade.this.listener.onFullscreenClosed();
        }

        public void onInterstitialFinished() {
        }

        public void onInterstitialClicked(InterstitialAd interstitialAd) {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_MOBFOX_NATIVE_SDK_CLICK, StatController.generateQueryParametersForSdk(MobfoxFullscreenFacade.this.configPhp, MobfoxFullscreenFacade.this.context, AdsType.FULLSCREEN, "mobfox fullscreen sdk", StatController.KEY_MOBFOX), MobfoxFullscreenFacade.this.context, false);
            MobfoxFullscreenFacade.this.listener.onFullscreenClicked();
        }

        public void onInterstitialShown(InterstitialAd interstitialAd) {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_MOBFOX_NATIVE_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(MobfoxFullscreenFacade.this.configPhp, MobfoxFullscreenFacade.this.context, AdsType.FULLSCREEN, "mobfox fullscreen sdk", StatController.KEY_MOBFOX), MobfoxFullscreenFacade.this.context, false);
            MobfoxFullscreenFacade.this.listener.onFullscreenOpened();
        }
    }

    public MobfoxFullscreenFacade(Context context, ConfigPhp configPhp) {
        super(context, configPhp);
    }

    protected void init() {
        this.interstitialAd = new InterstitialAd(this.context);
        this.interstitialAd.setInventoryHash(((AdNetworkSdkModel) this.configPhp.getFullscreenSdk().get(StatController.KEY_MOBFOX)).getPlacementId());
        this.interstitialAd.setListener(new C12581());
    }

    public void loadFullscreenAd() {
        this.interstitialAd.load();
    }

    public void showFullscreenAd() {
        this.interstitialAd.show();
    }

    public boolean isFullscreenLoaded() {
        return this.interstitialAd.isReady();
    }

    public void onPause() {
        super.onPause();
        this.interstitialAd.onPause();
    }

    public void onResume() {
        super.onResume();
        this.interstitialAd.onResume();
    }
}
