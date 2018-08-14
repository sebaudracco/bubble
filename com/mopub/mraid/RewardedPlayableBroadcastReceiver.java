package com.mopub.mraid;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.IntentActions;
import com.mopub.mobileads.BaseBroadcastReceiver;

public class RewardedPlayableBroadcastReceiver extends BaseBroadcastReceiver {
    private static IntentFilter sIntentFilter;
    @Nullable
    private RewardedMraidInterstitial$RewardedMraidInterstitialListener mRewardedMraidListener;

    public RewardedPlayableBroadcastReceiver(@Nullable RewardedMraidInterstitial$RewardedMraidInterstitialListener rewardedVideoListener, long broadcastIdentifier) {
        super(broadcastIdentifier);
        this.mRewardedMraidListener = rewardedVideoListener;
        getIntentFilter();
    }

    @NonNull
    public IntentFilter getIntentFilter() {
        if (sIntentFilter == null) {
            sIntentFilter = new IntentFilter();
            sIntentFilter.addAction(IntentActions.ACTION_REWARDED_PLAYABLE_COMPLETE);
        }
        return sIntentFilter;
    }

    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (this.mRewardedMraidListener != null && shouldConsumeBroadcast(intent)) {
            if (IntentActions.ACTION_REWARDED_PLAYABLE_COMPLETE.equals(intent.getAction())) {
                this.mRewardedMraidListener.onMraidComplete();
                unregister(this);
            }
        }
    }
}
