package com.mopub.mobileads;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.mopub.common.Preconditions;
import com.mopub.mraid.RewardedMraidController;

public class RewardedMraidCountdownRunnable extends RepeatingHandlerRunnable {
    private int mCurrentElapsedTimeMillis;
    @NonNull
    private final RewardedMraidController mRewardedMraidController;

    public RewardedMraidCountdownRunnable(@NonNull RewardedMraidController rewardedMraidController, @NonNull Handler handler) {
        super(handler);
        Preconditions.checkNotNull(handler);
        Preconditions.checkNotNull(rewardedMraidController);
        this.mRewardedMraidController = rewardedMraidController;
    }

    public void doWork() {
        this.mCurrentElapsedTimeMillis = (int) (((long) this.mCurrentElapsedTimeMillis) + this.mUpdateIntervalMillis);
        this.mRewardedMraidController.updateCountdown(this.mCurrentElapsedTimeMillis);
        if (this.mRewardedMraidController.isPlayableCloseable()) {
            this.mRewardedMraidController.showPlayableCloseButton();
        }
    }

    @VisibleForTesting
    @Deprecated
    int getCurrentElapsedTimeMillis() {
        return this.mCurrentElapsedTimeMillis;
    }
}
