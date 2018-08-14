package com.google.android.gms.internal.ads;

final /* synthetic */ class zzux implements Runnable {
    private final zzuw zzbpr;
    private final String zzzo;

    zzux(zzuw com_google_android_gms_internal_ads_zzuw, String str) {
        this.zzbpr = com_google_android_gms_internal_ads_zzuw;
        this.zzzo = str;
    }

    public final void run() {
        this.zzbpr.zzbi(this.zzzo);
    }
}
