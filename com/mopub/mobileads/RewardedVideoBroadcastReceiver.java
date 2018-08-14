package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.IntentActions;
import com.mopub.common.Preconditions;

public class RewardedVideoBroadcastReceiver extends BaseBroadcastReceiver {
    private static IntentFilter sIntentFilter;
    @Nullable
    private RewardedVastVideoInterstitial$RewardedVideoInterstitialListener mRewardedVideoListener;

    public RewardedVideoBroadcastReceiver(@Nullable RewardedVastVideoInterstitial$RewardedVideoInterstitialListener rewardedVideoListener, long broadcastIdentifier) {
        super(broadcastIdentifier);
        this.mRewardedVideoListener = rewardedVideoListener;
        getIntentFilter();
    }

    @NonNull
    public IntentFilter getIntentFilter() {
        if (sIntentFilter == null) {
            sIntentFilter = new IntentFilter();
            sIntentFilter.addAction(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
        }
        return sIntentFilter;
    }

    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intent);
        if (this.mRewardedVideoListener != null && shouldConsumeBroadcast(intent)) {
            if (IntentActions.ACTION_REWARDED_VIDEO_COMPLETE.equals(intent.getAction())) {
                this.mRewardedVideoListener.onVideoComplete();
                unregister(this);
            }
        }
    }
}
