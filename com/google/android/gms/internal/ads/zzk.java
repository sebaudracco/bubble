package com.google.android.gms.internal.ads;

final class zzk implements Runnable {
    private final zzr zzw;
    private final zzx zzx;
    private final Runnable zzy;

    public zzk(zzi com_google_android_gms_internal_ads_zzi, zzr com_google_android_gms_internal_ads_zzr, zzx com_google_android_gms_internal_ads_zzx, Runnable runnable) {
        this.zzw = com_google_android_gms_internal_ads_zzr;
        this.zzx = com_google_android_gms_internal_ads_zzx;
        this.zzy = runnable;
    }

    public final void run() {
        this.zzw.isCanceled();
        if ((this.zzx.zzbh == null ? 1 : null) != null) {
            this.zzw.zza(this.zzx.result);
        } else {
            this.zzw.zzb(this.zzx.zzbh);
        }
        if (this.zzx.zzbi) {
            this.zzw.zzb("intermediate-response");
        } else {
            this.zzw.zzc("done");
        }
        if (this.zzy != null) {
            this.zzy.run();
        }
    }
}
