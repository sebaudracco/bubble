package com.google.android.gms.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.zza;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.internal.ads.zzma;

public final class InterstitialAd {
    private final zzma zzuv;

    public InterstitialAd(Context context) {
        this.zzuv = new zzma(context);
        Preconditions.checkNotNull(context, "Context cannot be null");
    }

    public final AdListener getAdListener() {
        return this.zzuv.getAdListener();
    }

    public final String getAdUnitId() {
        return this.zzuv.getAdUnitId();
    }

    public final String getMediationAdapterClassName() {
        return this.zzuv.getMediationAdapterClassName();
    }

    public final boolean isLoaded() {
        return this.zzuv.isLoaded();
    }

    public final boolean isLoading() {
        return this.zzuv.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(AdRequest adRequest) {
        this.zzuv.zza(adRequest.zzay());
    }

    public final void setAdListener(AdListener adListener) {
        this.zzuv.setAdListener(adListener);
        if (adListener != null && (adListener instanceof zzjd)) {
            this.zzuv.zza((zzjd) adListener);
        } else if (adListener == null) {
            this.zzuv.zza(null);
        }
    }

    public final void setAdUnitId(String str) {
        this.zzuv.setAdUnitId(str);
    }

    public final void setImmersiveMode(boolean z) {
        this.zzuv.setImmersiveMode(z);
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzuv.setRewardedVideoAdListener(rewardedVideoAdListener);
    }

    public final void show() {
        this.zzuv.show();
    }

    public final void zza(zza com_google_android_gms_ads_reward_zza) {
        this.zzuv.zza(com_google_android_gms_ads_reward_zza);
    }

    public final void zza(boolean z) {
        this.zzuv.zza(true);
    }

    public final Bundle zzba() {
        return this.zzuv.zzba();
    }
}
