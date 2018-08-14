package com.google.android.gms.internal.ads;

final class zzs implements Runnable {
    private final /* synthetic */ String zzas;
    private final /* synthetic */ long zzat;
    private final /* synthetic */ zzr zzau;

    zzs(zzr com_google_android_gms_internal_ads_zzr, String str, long j) {
        this.zzau = com_google_android_gms_internal_ads_zzr;
        this.zzas = str;
        this.zzat = j;
    }

    public final void run() {
        this.zzau.zzae.zza(this.zzas, this.zzat);
        this.zzau.zzae.zzc(toString());
    }
}
