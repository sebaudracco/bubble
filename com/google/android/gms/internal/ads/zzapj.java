package com.google.android.gms.internal.ads;

final /* synthetic */ class zzapj implements Runnable {
    private final zzapg zzcyc;

    private zzapj(zzapg com_google_android_gms_internal_ads_zzapg) {
        this.zzcyc = com_google_android_gms_internal_ads_zzapg;
    }

    static Runnable zza(zzapg com_google_android_gms_internal_ads_zzapg) {
        return new zzapj(com_google_android_gms_internal_ads_zzapg);
    }

    public final void run() {
        this.zzcyc.stop();
    }
}
