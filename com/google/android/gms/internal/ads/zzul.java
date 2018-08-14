package com.google.android.gms.internal.ads;

final /* synthetic */ class zzul implements Runnable {
    private final zzuf zzbpk;
    private final String zzzo;

    zzul(zzuf com_google_android_gms_internal_ads_zzuf, String str) {
        this.zzbpk = com_google_android_gms_internal_ads_zzuf;
        this.zzzo = str;
    }

    public final void run() {
        this.zzbpk.zzbf(this.zzzo);
    }
}
