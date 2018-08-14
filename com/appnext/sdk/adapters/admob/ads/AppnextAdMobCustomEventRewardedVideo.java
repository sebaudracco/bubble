package com.appnext.sdk.adapters.admob.ads;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.appnext.ads.fullscreen.RewardedConfig;
import com.appnext.ads.fullscreen.RewardedServerSidePostback;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.core.Ad;
import java.io.Serializable;

public class AppnextAdMobCustomEventRewardedVideo extends AppnextAdMobCustomEvent {

    private class CustomEventRewardedVideoAd extends RewardedVideo {
        protected static final String TID = "321";

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

    protected Ad createAd(Context context, String str, Bundle bundle) {
        Ad ad;
        if (bundle != null) {
            try {
                Serializable serializable = bundle.getSerializable("AppnextConfiguration");
                Serializable serializable2 = bundle.getSerializable("AppnextRewardPostback");
            } catch (Throwable th) {
                Throwable th2 = th;
                ad = null;
                this.mListener.onAdFailedToLoad(0);
                Log.e("AppnextAdMob", "AppnextAdMobCustomEventRewardedVideo createAd: " + th2.getMessage());
                return ad;
            }
        }
        serializable2 = null;
        serializable = null;
        if (serializable == null || !(serializable instanceof RewardedConfig)) {
            ad = new CustomEventRewardedVideoAd(context, str);
        } else {
            ad = new CustomEventRewardedVideoAd(context, str, (RewardedConfig) serializable);
        }
        if (serializable2 != null) {
            try {
                if (serializable2 instanceof RewardedServerSidePostback) {
                    RewardedServerSidePostback rewardedServerSidePostback = (RewardedServerSidePostback) serializable2;
                    ad.setRewardedServerSidePostback(rewardedServerSidePostback.getRewardsTransactionId(), rewardedServerSidePostback.getRewardsUserId(), rewardedServerSidePostback.getRewardsRewardTypeCurrency(), rewardedServerSidePostback.getRewardsAmountRewarded(), rewardedServerSidePostback.getRewardsCustomParameter());
                }
            } catch (Throwable th3) {
                th2 = th3;
                this.mListener.onAdFailedToLoad(0);
                Log.e("AppnextAdMob", "AppnextAdMobCustomEventRewardedVideo createAd: " + th2.getMessage());
                return ad;
            }
        }
        return ad;
    }
}
