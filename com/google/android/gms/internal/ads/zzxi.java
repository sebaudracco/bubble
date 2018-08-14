package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzxi implements Callable<zzxe> {
    private final /* synthetic */ zzxb zzbuj;
    private final /* synthetic */ zzxh zzbuk;

    zzxi(zzxh com_google_android_gms_internal_ads_zzxh, zzxb com_google_android_gms_internal_ads_zzxb) {
        this.zzbuk = com_google_android_gms_internal_ads_zzxh;
        this.zzbuj = com_google_android_gms_internal_ads_zzxb;
    }

    private final zzxe zzmn() throws Exception {
        synchronized (zzxh.zza(this.zzbuk)) {
            if (zzxh.zzb(this.zzbuk)) {
                return null;
            }
            return this.zzbuj.zza(zzxh.zzc(this.zzbuk), zzxh.zzd(this.zzbuk));
        }
    }

    public final /* synthetic */ Object call() throws Exception {
        return zzmn();
    }
}
