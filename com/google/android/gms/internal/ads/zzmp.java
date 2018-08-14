package com.google.android.gms.internal.ads;

final class zzmp implements Runnable {
    private final /* synthetic */ zzmo zzatm;

    zzmp(zzmo com_google_android_gms_internal_ads_zzmo) {
        this.zzatm = com_google_android_gms_internal_ads_zzmo;
    }

    public final void run() {
        if (this.zzatm.zzatl != null) {
            try {
                this.zzatm.zzatl.onRewardedVideoAdFailedToLoad(1);
            } catch (Throwable e) {
                zzane.zzc("Could not notify onRewardedVideoAdFailedToLoad event.", e);
            }
        }
    }
}
