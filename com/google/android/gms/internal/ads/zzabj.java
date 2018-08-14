package com.google.android.gms.internal.ads;

final class zzabj implements Runnable {
    private final /* synthetic */ zzajh zzaam;
    private final /* synthetic */ zzabh zzbzk;

    zzabj(zzabh com_google_android_gms_internal_ads_zzabh, zzajh com_google_android_gms_internal_ads_zzajh) {
        this.zzbzk = com_google_android_gms_internal_ads_zzabh;
        this.zzaam = com_google_android_gms_internal_ads_zzajh;
    }

    public final void run() {
        synchronized (this.zzbzk.mLock) {
            zzabh com_google_android_gms_internal_ads_zzabh = this.zzbzk;
            com_google_android_gms_internal_ads_zzabh.zzbzd.zzb(this.zzaam);
        }
    }
}
