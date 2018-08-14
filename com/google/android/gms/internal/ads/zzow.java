package com.google.android.gms.internal.ads;

final class zzow implements Runnable {
    private final /* synthetic */ zzov zzbis;

    zzow(zzov com_google_android_gms_internal_ads_zzov) {
        this.zzbis = com_google_android_gms_internal_ads_zzov;
    }

    public final void run() {
        if (zzov.zzb(this.zzbis) != null) {
            zzov.zzb(this.zzbis).zzkq();
            zzov.zzb(this.zzbis).zzkp();
            zzov.zzb(this.zzbis).zzcs();
        }
        zzov.zza(this.zzbis, null);
    }
}
