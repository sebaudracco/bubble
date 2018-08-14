package com.appnext.sdk.adapters.mopub.ads;

import android.content.Context;
import android.util.Log;
import com.appnext.ads.fullscreen.FullScreenVideo;
import com.appnext.ads.fullscreen.FullscreenConfig;
import com.appnext.core.Ad;
import com.appnext.core.Configuration;
import java.util.Map;

public class AppnextMoPubCustomEventFullScreenVideo extends AppnextMoPubCustomEvent {

    private class CustomEventFullScreenVideoAd extends FullScreenVideo {
        protected static final String TID = "311";

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

    protected Ad createAd(Context context, Map<String, Object> map, Map<String, String> map2) {
        if (map != null) {
            try {
                FullscreenConfig fullscreenConfig = map.get("AppnextConfiguration");
            } catch (Throwable th) {
                Log.e("AppnextMoPub", "AppnextMoPubCustomEventFullScreenVideo createAd: " + th.getMessage());
                return null;
            }
        }
        fullscreenConfig = null;
        String appnextPlacementIdExtraKey = Helper.getAppnextPlacementIdExtraKey(map2);
        if (hasAdConfigServerExtras(map2)) {
            FullscreenConfig fullscreenConfig2;
            if (fullscreenConfig == null) {
                fullscreenConfig2 = new FullscreenConfig();
            } else {
                fullscreenConfig2 = fullscreenConfig;
            }
            setConfigFromExtras(fullscreenConfig2, map2);
            Object obj = fullscreenConfig2;
        }
        if (obj == null || !(obj instanceof FullscreenConfig)) {
            return new CustomEventFullScreenVideoAd(context, appnextPlacementIdExtraKey);
        }
        return new CustomEventFullScreenVideoAd(context, appnextPlacementIdExtraKey, (FullscreenConfig) obj);
    }

    protected boolean hasAdConfigServerExtras(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        if (Helper.hasAdConfigServerExtras(map) || Helper.hasVideoConfigServerExtras(map)) {
            return true;
        }
        return false;
    }

    protected void setConfigFromExtras(Configuration configuration, Map<String, String> map) {
        if (configuration != null && (configuration instanceof FullscreenConfig)) {
            FullscreenConfig fullscreenConfig = (FullscreenConfig) configuration;
            Helper.setConfigFromExtras(fullscreenConfig, map);
            Helper.setVideoConfigFromExtras(fullscreenConfig, map);
            Helper.setFullscreenConfigFromExtras(fullscreenConfig, map);
        }
    }
}
