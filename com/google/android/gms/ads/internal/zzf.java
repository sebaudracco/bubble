package com.google.android.gms.ads.internal;

final /* synthetic */ class zzf implements Runnable {
    private final zzbl zzwj;

    private zzf(zzbl com_google_android_gms_ads_internal_zzbl) {
        this.zzwj = com_google_android_gms_ads_internal_zzbl;
    }

    static Runnable zza(zzbl com_google_android_gms_ads_internal_zzbl) {
        return new zzf(com_google_android_gms_ads_internal_zzbl);
    }

    public final void run() {
        this.zzwj.resume();
    }
}
