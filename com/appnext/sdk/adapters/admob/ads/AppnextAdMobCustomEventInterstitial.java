package com.appnext.sdk.adapters.admob.ads;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.appnext.ads.interstitial.Interstitial;
import com.appnext.ads.interstitial.InterstitialConfig;
import com.appnext.core.Ad;
import java.io.Serializable;

public class AppnextAdMobCustomEventInterstitial extends AppnextAdMobCustomEvent {

    private class CustomEventInterstitialAd extends Interstitial {
        protected static final String TID = "321";

        public CustomEventInterstitialAd(Context context, String str) {
            super(context, str);
        }

        public CustomEventInterstitialAd(Context context, String str, InterstitialConfig interstitialConfig) {
            super(context, str, interstitialConfig);
        }

        public String getTID() {
            return TID;
        }
    }

    protected Ad createAd(Context context, String str, Bundle bundle) {
        if (bundle != null) {
            try {
                Serializable serializable = bundle.getSerializable("AppnextConfiguration");
            } catch (Throwable th) {
                this.mListener.onAdFailedToLoad(0);
                Log.e("AppnextAdMob", "AppnextAdMobCustomEventInterstitial createAd: " + th.getMessage());
                return null;
            }
        }
        serializable = null;
        if (serializable == null || !(serializable instanceof InterstitialConfig)) {
            return new CustomEventInterstitialAd(context, str);
        }
        return new CustomEventInterstitialAd(context, str, (InterstitialConfig) serializable);
    }
}
