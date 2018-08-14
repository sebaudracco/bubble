package com.google.android.gms.internal.ads;

final class zzapb implements Runnable {
    private final /* synthetic */ zzaov zzcxf;
    private final /* synthetic */ int zzcxi;
    private final /* synthetic */ int zzcxj;

    zzapb(zzaov com_google_android_gms_internal_ads_zzaov, int i, int i2) {
        this.zzcxf = com_google_android_gms_internal_ads_zzaov;
        this.zzcxi = i;
        this.zzcxj = i2;
    }

    public final void run() {
        if (zzaov.zza(this.zzcxf) != null) {
            zzaov.zza(this.zzcxf).zzf(this.zzcxi, this.zzcxj);
        }
    }
}
