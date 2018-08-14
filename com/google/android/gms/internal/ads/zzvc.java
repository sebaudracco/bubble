package com.google.android.gms.internal.ads;

final class zzvc implements Runnable {
    private final /* synthetic */ zzuw zzbpu;
    private final /* synthetic */ String zzbpv;

    zzvc(zzuw com_google_android_gms_internal_ads_zzuw, String str) {
        this.zzbpu = com_google_android_gms_internal_ads_zzuw;
        this.zzbpv = str;
    }

    public final void run() {
        zzuw.zza(this.zzbpu).loadUrl(this.zzbpv);
    }
}
