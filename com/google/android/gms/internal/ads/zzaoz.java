package com.google.android.gms.internal.ads;

final class zzaoz implements Runnable {
    private final /* synthetic */ zzaov zzcxf;
    private final /* synthetic */ String zzcxg;
    private final /* synthetic */ String zzcxh;

    zzaoz(zzaov com_google_android_gms_internal_ads_zzaov, String str, String str2) {
        this.zzcxf = com_google_android_gms_internal_ads_zzaov;
        this.zzcxg = str;
        this.zzcxh = str2;
    }

    public final void run() {
        if (zzaov.zza(this.zzcxf) != null) {
            zzaov.zza(this.zzcxf).zzg(this.zzcxg, this.zzcxh);
        }
    }
}
