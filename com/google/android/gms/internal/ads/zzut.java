package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final /* synthetic */ class zzut implements Runnable {
    private final zzus zzbpo;
    private final zzv zzbpp;
    private final Map zzbpq;

    zzut(zzus com_google_android_gms_internal_ads_zzus, zzv com_google_android_gms_ads_internal_gmsg_zzv, Map map) {
        this.zzbpo = com_google_android_gms_internal_ads_zzus;
        this.zzbpp = com_google_android_gms_ads_internal_gmsg_zzv;
        this.zzbpq = map;
    }

    public final void run() {
        zzus com_google_android_gms_internal_ads_zzus = this.zzbpo;
        this.zzbpp.zza(com_google_android_gms_internal_ads_zzus.getReference(), this.zzbpq);
    }
}
