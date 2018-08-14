package com.google.android.gms.internal.ads;

final class zzade implements Runnable {
    private final /* synthetic */ zzanf zzccd;
    private final /* synthetic */ String zzcce;

    zzade(zzadb com_google_android_gms_internal_ads_zzadb, zzanf com_google_android_gms_internal_ads_zzanf, String str) {
        this.zzccd = com_google_android_gms_internal_ads_zzanf;
        this.zzcce = str;
    }

    public final void run() {
        this.zzccd.zzcz(this.zzcce);
    }
}
