package com.google.android.gms.internal.ads;

final class zzape implements Runnable {
    private final /* synthetic */ zzaov zzcxf;

    zzape(zzaov com_google_android_gms_internal_ads_zzaov) {
        this.zzcxf = com_google_android_gms_internal_ads_zzaov;
    }

    public final void run() {
        if (zzaov.zza(this.zzcxf) != null) {
            zzaov.zza(this.zzcxf).onPaused();
        }
    }
}
