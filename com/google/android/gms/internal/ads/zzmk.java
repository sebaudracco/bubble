package com.google.android.gms.internal.ads;

final class zzmk implements Runnable {
    private final /* synthetic */ zzmj zzatk;

    zzmk(zzmj com_google_android_gms_internal_ads_zzmj) {
        this.zzatk = com_google_android_gms_internal_ads_zzmj;
    }

    public final void run() {
        if (this.zzatk.zzxs != null) {
            try {
                this.zzatk.zzxs.onAdFailedToLoad(1);
            } catch (Throwable e) {
                zzane.zzc("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
