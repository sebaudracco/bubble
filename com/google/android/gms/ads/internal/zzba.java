package com.google.android.gms.ads.internal;

final /* synthetic */ class zzba implements Runnable {
    private final Runnable zzxi;
    private final zzay zzzx;

    zzba(zzay com_google_android_gms_ads_internal_zzay, Runnable runnable) {
        this.zzzx = com_google_android_gms_ads_internal_zzay;
        this.zzxi = runnable;
    }

    public final void run() {
        this.zzzx.zza(this.zzxi);
    }
}
