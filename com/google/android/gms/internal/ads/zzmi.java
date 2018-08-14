package com.google.android.gms.internal.ads;

final class zzmi implements Runnable {
    private final /* synthetic */ zzmh zzatj;

    zzmi(zzmh com_google_android_gms_internal_ads_zzmh) {
        this.zzatj = com_google_android_gms_internal_ads_zzmh;
    }

    public final void run() {
        if (this.zzatj.zzati.zzxs != null) {
            try {
                this.zzatj.zzati.zzxs.onAdFailedToLoad(1);
            } catch (Throwable e) {
                zzane.zzc("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
