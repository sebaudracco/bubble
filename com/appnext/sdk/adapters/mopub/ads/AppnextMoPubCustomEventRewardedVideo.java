package com.appnext.sdk.adapters.mopub.ads;

import android.content.Context;
import android.util.Log;
import com.appnext.ads.fullscreen.RewardedConfig;
import com.appnext.ads.fullscreen.RewardedServerSidePostback;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.core.Ad;
import com.appnext.core.Configuration;
import java.util.Map;

public class AppnextMoPubCustomEventRewardedVideo extends AppnextMoPubCustomEvent {

    private class CustomEventRewardedVideoAd extends RewardedVideo {
        protected static final String TID = "311";

        public CustomEventRewardedVideoAd(Context context, String str) {
            super(context, str);
        }

        public CustomEventRewardedVideoAd(Context context, String str, RewardedConfig rewardedConfig) {
            super(context, str, rewardedConfig);
        }

        public String getTID() {
            return TID;
        }
    }

    protected Ad createAd(Context context, Map<String, Object> map, Map<String, String> map2) {
        Ad ad;
        if (map != null) {
            try {
                RewardedConfig rewardedConfig = map.get("AppnextConfiguration");
                Object obj = map.get("AppnextRewardPostback");
            } catch (Throwable th) {
                Throwable th2 = th;
                ad = null;
                Log.e("AppnextMoPub", "AppnextMoPubCustomEventRewardedVideo createAd: " + th2.getMessage());
                return ad;
            }
        }
        obj = null;
        rewardedConfig = null;
        String appnextPlacementIdExtraKey = Helper.getAppnextPlacementIdExtraKey(map2);
        if (hasAdConfigServerExtras(map2)) {
            RewardedConfig rewardedConfig2;
            if (rewardedConfig == null) {
                rewardedConfig2 = new RewardedConfig();
            } else {
                rewardedConfig2 = rewardedConfig;
            }
            setConfigFromExtras(rewardedConfig2, map2);
            Object obj2 = rewardedConfig2;
        }
        if (obj2 == null || !(obj2 instanceof RewardedConfig)) {
            ad = new CustomEventRewardedVideoAd(context, appnextPlacementIdExtraKey);
        } else {
            ad = new CustomEventRewardedVideoAd(context, appnextPlacementIdExtraKey, (RewardedConfig) obj2);
        }
        if (obj != null) {
            try {
                if (obj instanceof RewardedServerSidePostback) {
                    RewardedServerSidePostback rewardedServerSidePostback = (RewardedServerSidePostback) obj;
                    ad.setRewardedServerSidePostback(rewardedServerSidePostback.getRewardsTransactionId(), rewardedServerSidePostback.getRewardsUserId(), rewardedServerSidePostback.getRewardsRewardTypeCurrency(), rewardedServerSidePostback.getRewardsAmountRewarded(), rewardedServerSidePostback.getRewardsCustomParameter());
                }
            } catch (Throwable th3) {
                th2 = th3;
                Log.e("AppnextMoPub", "AppnextMoPubCustomEventRewardedVideo createAd: " + th2.getMessage());
                return ad;
            }
        }
        return ad;
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
        if (configuration != null && (configuration instanceof RewardedConfig)) {
            RewardedConfig rewardedConfig = (RewardedConfig) configuration;
            Helper.setConfigFromExtras(rewardedConfig, map);
            Helper.setVideoConfigFromExtras(rewardedConfig, map);
        }
    }
}
