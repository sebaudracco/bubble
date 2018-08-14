package com.mopub.mobileads;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.appsgeyser.sdk.configuration.Constants;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.AdTypeTranslator.CustomEventType;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

public abstract class MoPubRewardedAd extends CustomEventRewardedAd {
    @Nullable
    protected String mAdUnitId;
    private boolean mIsLoaded;
    private int mRewardedAdCurrencyAmount;
    @Nullable
    private String mRewardedAdCurrencyName;

    protected class MoPubRewardedAdListener implements CustomEventInterstitialListener {
        @NonNull
        private final Runnable mAdExpiration;
        @NonNull
        final Class<? extends MoPubRewardedAd> mCustomEventClass;
        @NonNull
        private Handler mHandler = new Handler();

        public MoPubRewardedAdListener(@NonNull Class<? extends MoPubRewardedAd> customEventClass) {
            Preconditions.checkNotNull(customEventClass);
            this.mCustomEventClass = customEventClass;
            this.mAdExpiration = new 1(this, MoPubRewardedAd.this);
        }

        public void onInterstitialLoaded() {
            MoPubRewardedAd.this.mIsLoaded = true;
            if (CustomEventType.isMoPubSpecific(this.mCustomEventClass.getName())) {
                this.mHandler.postDelayed(this.mAdExpiration, Constants.PULL_ALARM_PERIOD);
            }
            MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
        }

        public void onInterstitialFailed(MoPubErrorCode errorCode) {
            this.mHandler.removeCallbacks(this.mAdExpiration);
            switch (1.$SwitchMap$com$mopub$mobileads$MoPubErrorCode[errorCode.ordinal()]) {
                case 1:
                    MoPubRewardedVideoManager.onRewardedVideoPlaybackError(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId(), errorCode);
                    return;
                default:
                    MoPubRewardedVideoManager.onRewardedVideoLoadFailure(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId(), errorCode);
                    return;
            }
        }

        public void onInterstitialShown() {
            this.mHandler.removeCallbacks(this.mAdExpiration);
            MoPubRewardedVideoManager.onRewardedVideoStarted(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
        }

        public void onInterstitialClicked() {
            MoPubRewardedVideoManager.onRewardedVideoClicked(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
        }

        public void onLeaveApplication() {
        }

        public void onInterstitialDismissed() {
            MoPubRewardedVideoManager.onRewardedVideoClosed(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
            MoPubRewardedAd.this.onInvalidate();
        }

        @Deprecated
        @VisibleForTesting
        void setHandler(@NonNull Handler handler) {
            this.mHandler = handler;
        }
    }

    @Nullable
    protected LifecycleListener getLifecycleListener() {
        return null;
    }

    protected boolean checkAndInitializeSdk(@NonNull Activity launcherActivity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        return false;
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) throws Exception {
        Preconditions.checkNotNull(activity, "activity cannot be null");
        Preconditions.checkNotNull(localExtras, "localExtras cannot be null");
        Preconditions.checkNotNull(serverExtras, "serverExtras cannot be null");
        Object rewardedAdCurrencyName = localExtras.get(DataKeys.REWARDED_AD_CURRENCY_NAME_KEY);
        if (rewardedAdCurrencyName instanceof String) {
            this.mRewardedAdCurrencyName = (String) rewardedAdCurrencyName;
        } else {
            MoPubLog.d("No currency name specified for rewarded video. Using the default name.");
            this.mRewardedAdCurrencyName = "";
        }
        Object rewardedAdCurrencyAmount = localExtras.get(DataKeys.REWARDED_AD_CURRENCY_AMOUNT_STRING_KEY);
        if (rewardedAdCurrencyAmount instanceof String) {
            try {
                this.mRewardedAdCurrencyAmount = Integer.parseInt((String) rewardedAdCurrencyAmount);
            } catch (NumberFormatException e) {
                MoPubLog.d("Unable to convert currency amount: " + rewardedAdCurrencyAmount + ". Using the default reward amount: " + 0);
                this.mRewardedAdCurrencyAmount = 0;
            }
        } else {
            MoPubLog.d("No currency amount specified for rewarded ad. Using the default reward amount: 0");
            this.mRewardedAdCurrencyAmount = 0;
        }
        if (this.mRewardedAdCurrencyAmount < 0) {
            MoPubLog.d("Negative currency amount specified for rewarded ad. Using the default reward amount: 0");
            this.mRewardedAdCurrencyAmount = 0;
        }
        Object adUnitId = localExtras.get(DataKeys.AD_UNIT_ID_KEY);
        if (adUnitId instanceof String) {
            this.mAdUnitId = (String) adUnitId;
        } else {
            MoPubLog.d("Unable to set ad unit for rewarded ad.");
        }
    }

    protected void onInvalidate() {
        this.mIsLoaded = false;
    }

    protected boolean isReady() {
        return this.mIsLoaded;
    }

    @Nullable
    protected String getRewardedAdCurrencyName() {
        return this.mRewardedAdCurrencyName;
    }

    protected int getRewardedAdCurrencyAmount() {
        return this.mRewardedAdCurrencyAmount;
    }

    @Deprecated
    @VisibleForTesting
    void setIsLoaded(boolean isLoaded) {
        this.mIsLoaded = isLoaded;
    }

    @Deprecated
    @VisibleForTesting
    MoPubRewardedAdListener createListener(@NonNull Class<? extends MoPubRewardedAd> customEventClass) {
        return new MoPubRewardedAdListener(customEventClass);
    }
}
