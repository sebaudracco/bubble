package com.google.android.gms.internal.ads;

final /* synthetic */ class zzvi implements Runnable {
    private final zzvf zzbqc;
    private final zzvw zzbqf;
    private final zzuu zzbqg;

    zzvi(zzvf com_google_android_gms_internal_ads_zzvf, zzvw com_google_android_gms_internal_ads_zzvw, zzuu com_google_android_gms_internal_ads_zzuu) {
        this.zzbqc = com_google_android_gms_internal_ads_zzvf;
        this.zzbqf = com_google_android_gms_internal_ads_zzvw;
        this.zzbqg = com_google_android_gms_internal_ads_zzuu;
    }

    public final void run() {
        this.zzbqc.zza(this.zzbqf, this.zzbqg);
    }
}
