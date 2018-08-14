package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

@zzadh
public final class zzahj extends zzahf {
    @Nullable
    private RewardedVideoAdListener zzhc;

    public zzahj(@Nullable RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzhc = rewardedVideoAdListener;
    }

    @Nullable
    public final RewardedVideoAdListener getRewardedVideoAdListener() {
        return this.zzhc;
    }

    public final void onRewardedVideoAdClosed() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoAdClosed();
        }
    }

    public final void onRewardedVideoAdFailedToLoad(int i) {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoAdFailedToLoad(i);
        }
    }

    public final void onRewardedVideoAdLeftApplication() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoAdLeftApplication();
        }
    }

    public final void onRewardedVideoAdLoaded() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoAdLoaded();
        }
    }

    public final void onRewardedVideoAdOpened() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoAdOpened();
        }
    }

    public final void onRewardedVideoCompleted() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoCompleted();
        }
    }

    public final void onRewardedVideoStarted() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoStarted();
        }
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzhc = rewardedVideoAdListener;
    }

    public final void zza(zzagu com_google_android_gms_internal_ads_zzagu) {
        if (this.zzhc != null) {
            this.zzhc.onRewarded(new zzahh(com_google_android_gms_internal_ads_zzagu));
        }
    }
}
