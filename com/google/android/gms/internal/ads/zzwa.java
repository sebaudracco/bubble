package com.google.android.gms.internal.ads;

final /* synthetic */ class zzwa implements Runnable {
    private final zzvz zzbqx;
    private final zzuu zzbqy;

    zzwa(zzvz com_google_android_gms_internal_ads_zzvz, zzuu com_google_android_gms_internal_ads_zzuu) {
        this.zzbqx = com_google_android_gms_internal_ads_zzvz;
        this.zzbqy = com_google_android_gms_internal_ads_zzuu;
    }

    public final void run() {
        zzvz com_google_android_gms_internal_ads_zzvz = this.zzbqx;
        zzuu com_google_android_gms_internal_ads_zzuu = this.zzbqy;
        com_google_android_gms_internal_ads_zzvz.zzbqw.zzbpz.zze(com_google_android_gms_internal_ads_zzuu);
        com_google_android_gms_internal_ads_zzuu.destroy();
    }
}
