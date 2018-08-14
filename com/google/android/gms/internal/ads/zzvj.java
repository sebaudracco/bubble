package com.google.android.gms.internal.ads;

final /* synthetic */ class zzvj implements Runnable {
    private final zzuu zzbqh;

    private zzvj(zzuu com_google_android_gms_internal_ads_zzuu) {
        this.zzbqh = com_google_android_gms_internal_ads_zzuu;
    }

    static Runnable zza(zzuu com_google_android_gms_internal_ads_zzuu) {
        return new zzvj(com_google_android_gms_internal_ads_zzuu);
    }

    public final void run() {
        this.zzbqh.destroy();
    }
}
