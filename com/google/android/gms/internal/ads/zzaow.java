package com.google.android.gms.internal.ads;

final /* synthetic */ class zzaow implements Runnable {
    private final int zzcsi;
    private final zzaov zzcxe;

    zzaow(zzaov com_google_android_gms_internal_ads_zzaov, int i) {
        this.zzcxe = com_google_android_gms_internal_ads_zzaov;
        this.zzcsi = i;
    }

    public final void run() {
        this.zzcxe.zzah(this.zzcsi);
    }
}
