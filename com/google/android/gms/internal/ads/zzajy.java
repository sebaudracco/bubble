package com.google.android.gms.internal.ads;

final class zzajy implements Runnable {
    private final /* synthetic */ zzajx zzcqt;

    zzajy(zzajx com_google_android_gms_internal_ads_zzajx) {
        this.zzcqt = com_google_android_gms_internal_ads_zzajx;
    }

    public final void run() {
        zzajx.zza(this.zzcqt, Thread.currentThread());
        this.zzcqt.zzdn();
    }
}
