package com.google.android.gms.internal.ads;

final class zzadt implements Runnable {
    private final /* synthetic */ zzadk zzccn;
    private final /* synthetic */ zzaol zzcco;

    zzadt(zzadk com_google_android_gms_internal_ads_zzadk, zzaol com_google_android_gms_internal_ads_zzaol) {
        this.zzccn = com_google_android_gms_internal_ads_zzadk;
        this.zzcco = com_google_android_gms_internal_ads_zzaol;
    }

    public final void run() {
        synchronized (zzadk.zza(this.zzccn)) {
            this.zzccn.zzccj = this.zzccn.zza(zzadk.zzb(this.zzccn).zzacr, this.zzcco);
            if (this.zzccn.zzccj == null) {
                zzadk.zza(this.zzccn, 0, "Could not start the ad request service.");
                zzakk.zzcrm.removeCallbacks(zzadk.zzc(this.zzccn));
            }
        }
    }
}
