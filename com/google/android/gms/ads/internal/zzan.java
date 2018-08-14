package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzahu;
import com.google.android.gms.internal.ads.zzaig;

final class zzan implements zzahu {
    private final /* synthetic */ zzal zzza;

    zzan(zzal com_google_android_gms_ads_internal_zzal) {
        this.zzza = com_google_android_gms_ads_internal_zzal;
    }

    public final void onRewardedVideoAdClosed() {
        this.zzza.zzcb();
    }

    public final void onRewardedVideoAdLeftApplication() {
        this.zzza.zzbo();
    }

    public final void onRewardedVideoAdOpened() {
        this.zzza.zzcc();
    }

    public final void onRewardedVideoCompleted() {
        this.zzza.zzdl();
    }

    public final void onRewardedVideoStarted() {
        this.zzza.zzdk();
    }

    public final void zzc(zzaig com_google_android_gms_internal_ads_zzaig) {
        this.zzza.zzb(com_google_android_gms_internal_ads_zzaig);
    }

    public final void zzdm() {
        this.zzza.onAdClicked();
    }
}
