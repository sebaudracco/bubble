package com.google.android.gms.internal.ads;

final /* synthetic */ class zzarn implements Runnable {
    private final int zzcsi;
    private final int zzcsj;
    private final boolean zzdcp;
    private final boolean zzdcq;
    private final zzarl zzdel;

    zzarn(zzarl com_google_android_gms_internal_ads_zzarl, int i, int i2, boolean z, boolean z2) {
        this.zzdel = com_google_android_gms_internal_ads_zzarl;
        this.zzcsi = i;
        this.zzcsj = i2;
        this.zzdcp = z;
        this.zzdcq = z2;
    }

    public final void run() {
        this.zzdel.zza(this.zzcsi, this.zzcsj, this.zzdcp, this.zzdcq);
    }
}
