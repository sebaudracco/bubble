package com.appnext.sdk.adapters.mopub.ads;

import android.content.Context;
import android.util.Log;
import com.appnext.ads.interstitial.Interstitial;
import com.appnext.ads.interstitial.InterstitialConfig;
import com.appnext.core.Ad;
import com.appnext.core.Configuration;
import java.util.Map;

public class AppnextMoPubCustomEventInterstitial extends AppnextMoPubCustomEvent {

    private class CustomEventInterstitialAd extends Interstitial {
        protected static final String TID = "311";

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

    protected Ad createAd(Context context, Map<String, Object> map, Map<String, String> map2) {
        if (map != null) {
            try {
                InterstitialConfig interstitialConfig = map.get("AppnextConfiguration");
            } catch (Throwable th) {
                Log.e("AppnextMoPub", "AppnextMoPubCustomEventInterstitial createAd: " + th.getMessage());
                return null;
            }
        }
        interstitialConfig = null;
        String appnextPlacementIdExtraKey = Helper.getAppnextPlacementIdExtraKey(map2);
        if (hasAdConfigServerExtras(map2)) {
            InterstitialConfig interstitialConfig2;
            if (interstitialConfig == null) {
                interstitialConfig2 = new InterstitialConfig();
            } else {
                interstitialConfig2 = interstitialConfig;
            }
            setConfigFromExtras(interstitialConfig2, map2);
            Object obj = interstitialConfig2;
        }
        if (obj == null || !(obj instanceof InterstitialConfig)) {
            return new CustomEventInterstitialAd(context, appnextPlacementIdExtraKey);
        }
        return new CustomEventInterstitialAd(context, appnextPlacementIdExtraKey, (InterstitialConfig) obj);
    }

    protected boolean hasAdConfigServerExtras(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        if (Helper.hasAdConfigServerExtras(map) || map.containsKey("AppnextCreativeType") || map.containsKey("AppnextSkipText") || map.containsKey("AppnextAutoPlay")) {
            return true;
        }
        return false;
    }

    protected void setConfigFromExtras(Configuration configuration, Map<String, String> map) {
        if (configuration != null && (configuration instanceof InterstitialConfig)) {
            InterstitialConfig interstitialConfig = (InterstitialConfig) configuration;
            Helper.setConfigFromExtras(interstitialConfig, map);
            if (map.containsKey("AppnextCreativeType")) {
                try {
                    interstitialConfig.setCreativeType((String) map.get("AppnextCreativeType"));
                } catch (Throwable th) {
                    Log.e("AppnextMoPub", "setCreativeType: " + th.getMessage());
                }
            }
            if (map.containsKey("AppnextSkipText")) {
                try {
                    interstitialConfig.setSkipText((String) map.get("AppnextSkipText"));
                } catch (Throwable th2) {
                    Log.e("AppnextMoPub", "setSkipText: " + th2.getMessage());
                }
            }
            if (map.containsKey("AppnextAutoPlay")) {
                try {
                    interstitialConfig.setAutoPlay(Boolean.parseBoolean((String) map.get("AppnextAutoPlay")));
                } catch (Throwable th22) {
                    Log.e("AppnextMoPub", "setAutoPlay: " + th22.getMessage());
                }
            }
            if (map.containsKey("AppnextButtonColor")) {
                try {
                    interstitialConfig.setButtonColor((String) map.get("AppnextButtonColor"));
                } catch (Throwable th222) {
                    Log.e("AppnextMoPub", "setButtonColor: " + th222.getMessage());
                }
            }
            if (map.containsKey("AppnextBackButtonCanClose")) {
                try {
                    interstitialConfig.setBackButtonCanClose(Boolean.parseBoolean((String) map.get("AppnextBackButtonCanClose")));
                } catch (Throwable th2222) {
                    Log.e("AppnextMoPub", "setBackButtonCanClose: " + th2222.getMessage());
                }
            }
        }
    }
}
