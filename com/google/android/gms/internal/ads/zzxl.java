package com.google.android.gms.internal.ads;

final class zzxl implements Runnable {
    private final /* synthetic */ zzxe zzbun;

    zzxl(zzxk com_google_android_gms_internal_ads_zzxk, zzxe com_google_android_gms_internal_ads_zzxe) {
        this.zzbun = com_google_android_gms_internal_ads_zzxe;
    }

    public final void run() {
        try {
            this.zzbun.zzbtx.destroy();
        } catch (Throwable e) {
            zzakb.zzc("Could not destroy mediation adapter.", e);
        }
    }
}
