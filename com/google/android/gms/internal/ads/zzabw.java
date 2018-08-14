package com.google.android.gms.internal.ads;

final /* synthetic */ class zzabw implements Runnable {
    private final zzabv zzcaf;
    private final zzaoj zzcag;
    private final String zzcah;

    zzabw(zzabv com_google_android_gms_internal_ads_zzabv, zzaoj com_google_android_gms_internal_ads_zzaoj, String str) {
        this.zzcaf = com_google_android_gms_internal_ads_zzabv;
        this.zzcag = com_google_android_gms_internal_ads_zzaoj;
        this.zzcah = str;
    }

    public final void run() {
        this.zzcaf.zza(this.zzcag, this.zzcah);
    }
}
