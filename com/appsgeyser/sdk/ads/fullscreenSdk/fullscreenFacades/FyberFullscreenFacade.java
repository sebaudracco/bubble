package com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades.FullscreenSdkFacade.FullscreenAdListener;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.requesters.InterstitialRequester;
import com.fyber.requesters.RequestCallback;
import com.fyber.requesters.RequestError;

public class FyberFullscreenFacade extends AbstractFullscreenFacade {
    private Intent interstitialIntent;

    class C12551 implements RequestCallback {
        C12551() {
        }

        public void onAdAvailable(Intent intent) {
            FyberFullscreenFacade.this.interstitialIntent = intent;
            FyberFullscreenFacade.this.listener.onFullscreenReceived();
        }

        public void onAdNotAvailable(AdFormat adFormat) {
            FyberFullscreenFacade.this.listener.onFullscreenError("No ad available from Fyber");
        }

        public void onRequestError(RequestError requestError) {
            FyberFullscreenFacade.this.listener.onFullscreenError(requestError.getDescription());
        }
    }

    class C12562 implements FullscreenAdListener {
        C12562() {
        }

        public void onFullscreenReceived() {
        }

        public void onFullscreenOpened() {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FYBER_NATIVE_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(FyberFullscreenFacade.this.configPhp, FyberFullscreenFacade.this.context, AdsType.FULLSCREEN, "fyber fullscreen sdk", StatController.KEY_FYBER), FyberFullscreenFacade.this.context, false);
            FyberFullscreenFacade.this.listener.onFullscreenOpened();
        }

        public void onFullscreenClosed() {
            FyberFullscreenFacade.this.listener.onFullscreenClosed();
        }

        public void onFullscreenError(String message) {
            FyberFullscreenFacade.this.listener.onFullscreenError(message);
        }

        public void onFullscreenClicked() {
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FYBER_NATIVE_SDK_CLICK, StatController.generateQueryParametersForSdk(FyberFullscreenFacade.this.configPhp, FyberFullscreenFacade.this.context, AdsType.FULLSCREEN, "fyber fullscreen sdk", StatController.KEY_FYBER), FyberFullscreenFacade.this.context, false);
            FyberFullscreenFacade.this.listener.onFullscreenClicked();
        }
    }

    public FyberFullscreenFacade(Context context, ConfigPhp configPhp) {
        super(context, configPhp);
    }

    protected void init() {
        Fyber.with(((AdNetworkSdkModel) this.configPhp.getFullscreenSdk().get(StatController.KEY_FYBER)).getAppId(), (Activity) this.context).withSecurityToken(((AdNetworkSdkModel) this.configPhp.getFullscreenSdk().get(StatController.KEY_FYBER)).getPlacementId()).start();
    }

    public void loadFullscreenAd() {
        this.interstitialIntent = null;
        InterstitialRequester.create(new C12551());
    }

    public void showFullscreenAd() {
        FullscreenAdListener fullscreenAdListener = new C12562();
    }

    public boolean isFullscreenLoaded() {
        return this.interstitialIntent != null;
    }
}
