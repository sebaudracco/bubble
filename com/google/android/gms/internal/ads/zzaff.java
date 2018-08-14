package com.google.android.gms.internal.ads;

final class zzaff implements Runnable {
    private final /* synthetic */ zzafa zzcgj;

    zzaff(zzafa com_google_android_gms_internal_ads_zzafa) {
        this.zzcgj = com_google_android_gms_internal_ads_zzafa;
    }

    public final void run() {
        if (zzafa.zzb(this.zzcgj) != null) {
            zzafa.zzb(this.zzcgj).release();
            zzafa.zza(this.zzcgj, null);
        }
    }
}
