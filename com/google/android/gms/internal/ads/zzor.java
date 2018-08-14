package com.google.android.gms.internal.ads;

final class zzor implements Runnable {
    private final /* synthetic */ zzoq zzbin;

    zzor(zzoq com_google_android_gms_internal_ads_zzoq) {
        this.zzbin = com_google_android_gms_internal_ads_zzoq;
    }

    public final void run() {
        if (zzoq.zzb(this.zzbin) != null) {
            zzoq.zzb(this.zzbin).zzkq();
            zzoq.zzb(this.zzbin).zzkp();
            zzoq.zzb(this.zzbin).zzcs();
        }
        zzoq.zza(this.zzbin, null);
    }
}
