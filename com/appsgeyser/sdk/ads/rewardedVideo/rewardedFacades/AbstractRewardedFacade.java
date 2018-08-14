package com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades;

import android.content.Context;
import com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades.RewardedVideoFacade.RewardedVideoListener;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;

public abstract class AbstractRewardedFacade implements RewardedVideoFacade {
    protected ConfigPhp configPhp;
    protected Context context;
    protected RewardedVideoListener listener;
    protected int priority;

    protected abstract void init();

    protected abstract void setPriority();

    protected AbstractRewardedFacade(Context context, ConfigPhp configPhp) {
        this.context = context;
        this.configPhp = configPhp;
        setPriority();
        init();
    }

    public void setListener(RewardedVideoListener listener) {
        this.listener = listener;
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onDestroy() {
    }

    public int getPriority() {
        return this.priority;
    }
}
