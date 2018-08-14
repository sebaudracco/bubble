package com.google.ads.mediation;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

final class zza implements RewardedVideoAdListener {
    private final /* synthetic */ AbstractAdViewAdapter zzhd;

    zza(AbstractAdViewAdapter abstractAdViewAdapter) {
        this.zzhd = abstractAdViewAdapter;
    }

    public final void onRewarded(RewardItem rewardItem) {
        AbstractAdViewAdapter.zza(this.zzhd).onRewarded(this.zzhd, rewardItem);
    }

    public final void onRewardedVideoAdClosed() {
        AbstractAdViewAdapter.zza(this.zzhd).onAdClosed(this.zzhd);
        AbstractAdViewAdapter.zza(this.zzhd, null);
    }

    public final void onRewardedVideoAdFailedToLoad(int i) {
        AbstractAdViewAdapter.zza(this.zzhd).onAdFailedToLoad(this.zzhd, i);
    }

    public final void onRewardedVideoAdLeftApplication() {
        AbstractAdViewAdapter.zza(this.zzhd).onAdLeftApplication(this.zzhd);
    }

    public final void onRewardedVideoAdLoaded() {
        AbstractAdViewAdapter.zza(this.zzhd).onAdLoaded(this.zzhd);
    }

    public final void onRewardedVideoAdOpened() {
        AbstractAdViewAdapter.zza(this.zzhd).onAdOpened(this.zzhd);
    }

    public final void onRewardedVideoCompleted() {
        AbstractAdViewAdapter.zza(this.zzhd).onVideoCompleted(this.zzhd);
    }

    public final void onRewardedVideoStarted() {
        AbstractAdViewAdapter.zza(this.zzhd).onVideoStarted(this.zzhd);
    }
}
