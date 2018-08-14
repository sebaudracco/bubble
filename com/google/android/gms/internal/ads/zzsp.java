package com.google.android.gms.internal.ads;

final /* synthetic */ class zzsp implements Runnable {
    private final zzso zzbnq;
    private final zzsf zzbnr;
    private final zzaoj zzbns;
    private final zzsg zzbnt;

    zzsp(zzso com_google_android_gms_internal_ads_zzso, zzsf com_google_android_gms_internal_ads_zzsf, zzaoj com_google_android_gms_internal_ads_zzaoj, zzsg com_google_android_gms_internal_ads_zzsg) {
        this.zzbnq = com_google_android_gms_internal_ads_zzso;
        this.zzbnr = com_google_android_gms_internal_ads_zzsf;
        this.zzbns = com_google_android_gms_internal_ads_zzaoj;
        this.zzbnt = com_google_android_gms_internal_ads_zzsg;
    }

    public final void run() {
        zzso com_google_android_gms_internal_ads_zzso = this.zzbnq;
        zzsf com_google_android_gms_internal_ads_zzsf = this.zzbnr;
        zzaoj com_google_android_gms_internal_ads_zzaoj = this.zzbns;
        try {
            com_google_android_gms_internal_ads_zzaoj.set(com_google_android_gms_internal_ads_zzsf.zzlb().zza(this.zzbnt));
        } catch (Throwable e) {
            zzakb.zzb("Unable to obtain a cache service instance.", e);
            com_google_android_gms_internal_ads_zzaoj.setException(e);
            zzsm.zza(com_google_android_gms_internal_ads_zzso.zzbnn);
        }
    }
}
