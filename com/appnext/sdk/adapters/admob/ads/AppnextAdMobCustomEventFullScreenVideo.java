package com.appnext.sdk.adapters.admob.ads;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.appnext.ads.fullscreen.FullScreenVideo;
import com.appnext.ads.fullscreen.FullscreenConfig;
import com.appnext.core.Ad;
import java.io.Serializable;

public class AppnextAdMobCustomEventFullScreenVideo extends AppnextAdMobCustomEvent {

    private class CustomEventFullScreenVideoAd extends FullScreenVideo {
        protected static final String TID = "321";

        public CustomEventFullScreenVideoAd(Context context, String str) {
            super(context, str);
        }

        public CustomEventFullScreenVideoAd(Context context, String str, FullscreenConfig fullscreenConfig) {
            super(context, str, fullscreenConfig);
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
                Log.e("AppnextAdMob", "AppnextAdMobCustomEventFullScreenVideo createAd: " + th.getMessage());
                return null;
            }
        }
        serializable = null;
        if (serializable == null || !(serializable instanceof FullscreenConfig)) {
            return new CustomEventFullScreenVideoAd(context, str);
        }
        return new CustomEventFullScreenVideoAd(context, str, (FullscreenConfig) serializable);
    }
}
