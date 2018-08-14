package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest$ErrorCode;

final class zzza implements Runnable {
    private final /* synthetic */ zzyq zzbvd;
    private final /* synthetic */ AdRequest$ErrorCode zzbve;

    zzza(zzyq com_google_android_gms_internal_ads_zzyq, AdRequest$ErrorCode adRequest$ErrorCode) {
        this.zzbvd = com_google_android_gms_internal_ads_zzyq;
        this.zzbve = adRequest$ErrorCode;
    }

    public final void run() {
        try {
            zzyq.zza(this.zzbvd).onAdFailedToLoad(zzzc.zza(this.zzbve));
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
