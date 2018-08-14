package com.google.android.gms.internal.ads;

final class zzop implements Runnable {
    private final /* synthetic */ zzoo zzbik;

    zzop(zzoo com_google_android_gms_internal_ads_zzoo) {
        this.zzbik = com_google_android_gms_internal_ads_zzoo;
    }

    public final void run() {
        if (zzoo.zzb(this.zzbik) != null) {
            zzoo.zzb(this.zzbik).zzkq();
            zzoo.zzb(this.zzbik).zzkp();
            zzoo.zzb(this.zzbik).zzcs();
        }
        zzoo.zza(this.zzbik, null);
    }
}
