package com.google.android.gms.internal.ads;

final class zzdc implements Runnable {
    private final /* synthetic */ zzcz zzsl;
    private final /* synthetic */ int zzsm;
    private final /* synthetic */ boolean zzsn;

    zzdc(zzcz com_google_android_gms_internal_ads_zzcz, int i, boolean z) {
        this.zzsl = com_google_android_gms_internal_ads_zzcz;
        this.zzsm = i;
        this.zzsn = z;
    }

    public final void run() {
        zzba zzb = this.zzsl.zzb(this.zzsm, this.zzsn);
        this.zzsl.zzsb = zzb;
        if (zzcz.zza(this.zzsm, zzb)) {
            this.zzsl.zza(this.zzsm + 1, this.zzsn);
        }
    }
}
