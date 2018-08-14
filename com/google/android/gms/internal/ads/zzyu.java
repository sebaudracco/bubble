package com.google.android.gms.internal.ads;

final class zzyu implements Runnable {
    private final /* synthetic */ zzyq zzbvd;

    zzyu(zzyq com_google_android_gms_internal_ads_zzyq) {
        this.zzbvd = com_google_android_gms_internal_ads_zzyq;
    }

    public final void run() {
        try {
            zzyq.zza(this.zzbvd).onAdClosed();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
