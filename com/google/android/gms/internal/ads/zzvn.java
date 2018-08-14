package com.google.android.gms.internal.ads;

final /* synthetic */ class zzvn implements Runnable {
    private final zzuu zzbqh;

    private zzvn(zzuu com_google_android_gms_internal_ads_zzuu) {
        this.zzbqh = com_google_android_gms_internal_ads_zzuu;
    }

    static Runnable zza(zzuu com_google_android_gms_internal_ads_zzuu) {
        return new zzvn(com_google_android_gms_internal_ads_zzuu);
    }

    public final void run() {
        this.zzbqh.destroy();
    }
}
