package com.google.android.gms.internal.ads;

final class zzaby implements Runnable {
    private final /* synthetic */ zzaoj zzcaj;
    private final /* synthetic */ String zzcak;
    private final /* synthetic */ zzabv zzcal;

    zzaby(zzabv com_google_android_gms_internal_ads_zzabv, zzaoj com_google_android_gms_internal_ads_zzaoj, String str) {
        this.zzcal = com_google_android_gms_internal_ads_zzabv;
        this.zzcaj = com_google_android_gms_internal_ads_zzaoj;
        this.zzcak = str;
    }

    public final void run() {
        this.zzcaj.set((zzrf) zzabv.zza(this.zzcal).zzdv().get(this.zzcak));
    }
}
