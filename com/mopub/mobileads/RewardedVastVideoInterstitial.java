package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.mopub.common.VisibleForTesting;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

class RewardedVastVideoInterstitial extends VastVideoInterstitial {
    @Nullable
    private RewardedVideoBroadcastReceiver mRewardedVideoBroadcastReceiver;

    RewardedVastVideoInterstitial() {
    }

    public void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        super.loadInterstitial(context, customEventInterstitialListener, localExtras, serverExtras);
        if (customEventInterstitialListener instanceof RewardedVideoInterstitialListener) {
            this.mRewardedVideoBroadcastReceiver = new RewardedVideoBroadcastReceiver((RewardedVideoInterstitialListener) customEventInterstitialListener, this.mBroadcastIdentifier);
            this.mRewardedVideoBroadcastReceiver.register(this.mRewardedVideoBroadcastReceiver, context);
        }
    }

    public void onVastVideoConfigurationPrepared(VastVideoConfig vastVideoConfig) {
        if (vastVideoConfig != null) {
            vastVideoConfig.setIsRewardedVideo(true);
        }
        super.onVastVideoConfigurationPrepared(vastVideoConfig);
    }

    public void onInvalidate() {
        super.onInvalidate();
        if (this.mRewardedVideoBroadcastReceiver != null) {
            this.mRewardedVideoBroadcastReceiver.unregister(this.mRewardedVideoBroadcastReceiver);
        }
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    RewardedVideoBroadcastReceiver getRewardedVideoBroadcastReceiver() {
        return this.mRewardedVideoBroadcastReceiver;
    }
}
