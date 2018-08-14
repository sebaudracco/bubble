package com.mopub.mraid;

import android.content.Context;
import android.support.annotation.Nullable;
import com.mopub.common.DataKeys;
import com.mopub.common.VisibleForTesting;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.RewardedMraidActivity;
import java.util.Map;

public class RewardedMraidInterstitial extends MraidInterstitial {
    private int mRewardedDuration;
    @Nullable
    private RewardedPlayableBroadcastReceiver mRewardedPlayableBroadcastReceiver;
    private boolean mShouldRewardOnClick;

    public void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        super.loadInterstitial(context, customEventInterstitialListener, localExtras, serverExtras);
        if (customEventInterstitialListener instanceof RewardedMraidInterstitialListener) {
            this.mRewardedPlayableBroadcastReceiver = new RewardedPlayableBroadcastReceiver((RewardedMraidInterstitialListener) customEventInterstitialListener, this.mBroadcastIdentifier);
            this.mRewardedPlayableBroadcastReceiver.register(this.mRewardedPlayableBroadcastReceiver, context);
        }
        Object rewardedDurationObject = localExtras.get(DataKeys.REWARDED_AD_DURATION_KEY);
        this.mRewardedDuration = rewardedDurationObject instanceof Integer ? ((Integer) rewardedDurationObject).intValue() : 30;
        Object shouldRewardOnClickObject = localExtras.get(DataKeys.SHOULD_REWARD_ON_CLICK_KEY);
        this.mShouldRewardOnClick = shouldRewardOnClickObject instanceof Boolean ? ((Boolean) shouldRewardOnClickObject).booleanValue() : false;
    }

    public void showInterstitial() {
        RewardedMraidActivity.start(this.mContext, this.mAdReport, this.mHtmlData, this.mBroadcastIdentifier, this.mRewardedDuration, this.mShouldRewardOnClick);
    }

    public void onInvalidate() {
        super.onInvalidate();
        if (this.mRewardedPlayableBroadcastReceiver != null) {
            this.mRewardedPlayableBroadcastReceiver.unregister(this.mRewardedPlayableBroadcastReceiver);
        }
    }

    @Deprecated
    @VisibleForTesting
    int getRewardedDuration() {
        return this.mRewardedDuration;
    }

    @Deprecated
    @VisibleForTesting
    boolean isShouldRewardOnClick() {
        return this.mShouldRewardOnClick;
    }
}
