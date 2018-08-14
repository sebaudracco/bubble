package com.google.android.gms.internal.ads;

final /* synthetic */ class zzvg implements Runnable {
    private final zzvf zzbqc;
    private final zzci zzbqd;
    private final zzvw zzbqe;

    zzvg(zzvf com_google_android_gms_internal_ads_zzvf, zzci com_google_android_gms_internal_ads_zzci, zzvw com_google_android_gms_internal_ads_zzvw) {
        this.zzbqc = com_google_android_gms_internal_ads_zzvf;
        this.zzbqd = com_google_android_gms_internal_ads_zzci;
        this.zzbqe = com_google_android_gms_internal_ads_zzvw;
    }

    public final void run() {
        this.zzbqc.zza(this.zzbqd, this.zzbqe);
    }
}
