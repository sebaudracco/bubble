package com.appnext.sdk.adapters.mopub.ads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.appnext.ads.AdsError;
import com.appnext.ads.fullscreen.RewardedConfig;
import com.appnext.ads.fullscreen.RewardedServerSidePostback;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.core.AppnextError;
import com.appnext.core.Configuration;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.appnext.core.callbacks.OnVideoEnded;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.CustomEventRewardedVideo;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoManager;
import java.util.Map;

public class AppnextMoPubRewardedVideo extends CustomEventRewardedVideo {
    RewardedVideo ad;
    String adUnitId = "";

    class C11711 implements OnVideoEnded {
        C11711() {
        }

        public void videoEnded() {
            MoPubRewardedVideoManager.onRewardedVideoCompleted(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId(), MoPubReward.success("", MoPubReward.NO_REWARD_AMOUNT));
        }
    }

    class C11722 implements OnAdLoaded {
        C11722() {
        }

        public void adLoaded(String str) {
            MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId());
        }
    }

    class C11733 implements OnAdOpened {
        C11733() {
        }

        public void adOpened() {
            MoPubRewardedVideoManager.onRewardedVideoStarted(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId());
        }
    }

    class C11744 implements OnAdClicked {
        C11744() {
        }

        public void adClicked() {
            MoPubRewardedVideoManager.onRewardedVideoClicked(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId());
        }
    }

    class C11755 implements OnAdClosed {
        C11755() {
        }

        public void onAdClosed() {
            MoPubRewardedVideoManager.onRewardedVideoClosed(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId());
        }
    }

    class C11766 implements OnAdError {
        C11766() {
        }

        public void adError(String str) {
            Object obj = -1;
            switch (str.hashCode()) {
                case -1958363695:
                    if (str.equals(AppnextError.NO_ADS)) {
                        obj = 4;
                        break;
                    }
                    break;
                case -1477010874:
                    if (str.equals(AppnextError.CONNECTION_ERROR)) {
                        obj = 3;
                        break;
                    }
                    break;
                case 297538105:
                    if (str.equals(AdsError.AD_NOT_READY)) {
                        obj = null;
                        break;
                    }
                    break;
                case 350741825:
                    if (str.equals("Timeout")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 844170097:
                    if (str.equals(AppnextError.SLOW_CONNECTION)) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    MoPubRewardedVideoManager.onRewardedVideoLoadFailure(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId(), MoPubErrorCode.WARMUP);
                    return;
                case 1:
                case 2:
                    MoPubRewardedVideoManager.onRewardedVideoLoadFailure(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId(), MoPubErrorCode.NETWORK_TIMEOUT);
                    return;
                case 3:
                    MoPubRewardedVideoManager.onRewardedVideoLoadFailure(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId(), MoPubErrorCode.NO_CONNECTION);
                    return;
                case 4:
                    MoPubRewardedVideoManager.onRewardedVideoLoadFailure(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId(), MoPubErrorCode.NO_FILL);
                    return;
                default:
                    MoPubRewardedVideoManager.onRewardedVideoLoadFailure(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId(), MoPubErrorCode.INTERNAL_ERROR);
                    return;
            }
        }
    }

    public static class AppnextMediationSettings implements MediationSettings {
        private String _rewardsAmountRewarded;
        private String _rewardsCustomParameter;
        private String _rewardsRewardTypeCurrency;
        private String _rewardsTransactionId;
        private String _rewardsUserId;

        public static class Builder {
            private String _rewardsAmountRewarded = "";
            private String _rewardsCustomParameter = "";
            private String _rewardsRewardTypeCurrency = "";
            private String _rewardsTransactionId = "";
            private String _rewardsUserId = "";

            public Builder withRewardsTransactionId(String str) {
                this._rewardsTransactionId = str;
                return this;
            }

            public Builder withRewardsUserId(String str) {
                this._rewardsUserId = str;
                return this;
            }

            public Builder withRewardsRewardTypeCurrency(String str) {
                this._rewardsRewardTypeCurrency = str;
                return this;
            }

            public Builder withRewardsAmountRewarded(String str) {
                this._rewardsAmountRewarded = str;
                return this;
            }

            public Builder withRewardsCustomParameter(String str) {
                this._rewardsCustomParameter = str;
                return this;
            }

            public AppnextMediationSettings build() {
                return new AppnextMediationSettings();
            }
        }

        private AppnextMediationSettings(Builder builder) {
            this._rewardsTransactionId = "";
            this._rewardsUserId = "";
            this._rewardsRewardTypeCurrency = "";
            this._rewardsAmountRewarded = "";
            this._rewardsCustomParameter = "";
            this._rewardsTransactionId = builder._rewardsTransactionId;
            this._rewardsUserId = builder._rewardsUserId;
            this._rewardsRewardTypeCurrency = builder._rewardsRewardTypeCurrency;
            this._rewardsAmountRewarded = builder._rewardsAmountRewarded;
            this._rewardsCustomParameter = builder._rewardsCustomParameter;
        }
    }

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

    protected boolean hasVideoAvailable() {
        return this.ad != null && this.ad.isAdLoaded();
    }

    protected void showVideo() {
        AppnextMediationSettings appnextMediationSettings;
        AppnextMediationSettings appnextMediationSettings2 = null;
        if (!this.adUnitId.equals("")) {
            appnextMediationSettings2 = (AppnextMediationSettings) MoPubRewardedVideoManager.getInstanceMediationSettings(AppnextMediationSettings.class, this.adUnitId);
        }
        if (appnextMediationSettings2 == null) {
            appnextMediationSettings = (AppnextMediationSettings) MoPubRewardedVideoManager.getGlobalMediationSettings(AppnextMediationSettings.class);
        } else {
            appnextMediationSettings = appnextMediationSettings2;
        }
        if (!(appnextMediationSettings == null || (appnextMediationSettings._rewardsAmountRewarded.equals("") && appnextMediationSettings._rewardsCustomParameter.equals("") && appnextMediationSettings._rewardsRewardTypeCurrency.equals("") && appnextMediationSettings._rewardsTransactionId.equals("") && appnextMediationSettings._rewardsUserId.equals("")))) {
            this.ad.setRewardedServerSidePostback(appnextMediationSettings._rewardsTransactionId, appnextMediationSettings._rewardsUserId, appnextMediationSettings._rewardsRewardTypeCurrency, appnextMediationSettings._rewardsAmountRewarded, appnextMediationSettings._rewardsCustomParameter);
        }
        if (this.ad != null) {
            this.ad.showAd();
        }
    }

    @Nullable
    protected LifecycleListener getLifecycleListener() {
        return null;
    }

    protected boolean checkAndInitializeSdk(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        if (this.ad != null) {
            return false;
        }
        try {
            init(activity, map, map2);
        } catch (Throwable th) {
            Log.e("AppnextMoPub", "AppnextMoPubRewardedVideo createAd: " + th.getMessage());
        }
        return true;
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        checkAndInitializeSdk(activity, map, map2);
        if (this.ad != null) {
            this.ad.loadAd();
        }
    }

    private void init(Activity activity, Map<String, Object> map, Map<String, String> map2) {
        Object obj;
        RewardedConfig rewardedConfig;
        Object obj2 = null;
        if (map != null) {
            RewardedConfig rewardedConfig2 = map.get("AppnextConfiguration");
            obj2 = map.get("AppnextRewardPostback");
            obj = map.get(DataKeys.AD_UNIT_ID_KEY);
            if (obj instanceof String) {
                this.adUnitId = (String) obj;
            }
            rewardedConfig = rewardedConfig2;
        } else {
            rewardedConfig = null;
        }
        String appnextPlacementIdExtraKey = Helper.getAppnextPlacementIdExtraKey(map2);
        if (hasAdConfigServerExtras(map2)) {
            if (rewardedConfig == null) {
                rewardedConfig2 = new RewardedConfig();
            } else {
                rewardedConfig2 = rewardedConfig;
            }
            setConfigFromExtras(rewardedConfig2, map2);
            obj = rewardedConfig2;
        }
        if (obj == null || !(obj instanceof RewardedConfig)) {
            this.ad = new CustomEventRewardedVideoAd(activity, appnextPlacementIdExtraKey);
        } else {
            this.ad = new CustomEventRewardedVideoAd(activity, appnextPlacementIdExtraKey, (RewardedConfig) obj);
        }
        if (obj2 != null && (obj2 instanceof RewardedServerSidePostback)) {
            RewardedServerSidePostback rewardedServerSidePostback = (RewardedServerSidePostback) obj2;
            this.ad.setRewardedServerSidePostback(rewardedServerSidePostback.getRewardsTransactionId(), rewardedServerSidePostback.getRewardsUserId(), rewardedServerSidePostback.getRewardsRewardTypeCurrency(), rewardedServerSidePostback.getRewardsAmountRewarded(), rewardedServerSidePostback.getRewardsCustomParameter());
        }
        this.ad.setOnVideoEndedCallback(new C11711());
        this.ad.setOnAdLoadedCallback(new C11722());
        this.ad.setOnAdOpenedCallback(new C11733());
        this.ad.setOnAdClickedCallback(new C11744());
        this.ad.setOnAdClosedCallback(new C11755());
        this.ad.setOnAdErrorCallback(new C11766());
    }

    @NonNull
    protected String getAdNetworkId() {
        return "appnext_id";
    }

    protected void onInvalidate() {
        if (this.ad != null) {
            this.ad.destroy();
        }
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
            Helper.setRewardedVideoConfigFromExtras(rewardedConfig, map);
        }
    }
}
