package com.google.android.gms.internal.ads;

final class zzyr implements Runnable {
    private final /* synthetic */ zzyq zzbvd;

    zzyr(zzyq com_google_android_gms_internal_ads_zzyq) {
        this.zzbvd = com_google_android_gms_internal_ads_zzyq;
    }

    public final void run() {
        try {
            zzyq.zza(this.zzbvd).onAdClicked();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
