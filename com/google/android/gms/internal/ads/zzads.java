package com.google.android.gms.internal.ads;

final class zzads implements Runnable {
    private final /* synthetic */ zzadk zzccn;

    zzads(zzadk com_google_android_gms_internal_ads_zzadk) {
        this.zzccn = com_google_android_gms_internal_ads_zzadk;
    }

    public final void run() {
        synchronized (zzadk.zza(this.zzccn)) {
            if (this.zzccn.zzccj == null) {
                return;
            }
            this.zzccn.onStop();
            zzadk.zza(this.zzccn, 2, "Timed out waiting for ad response.");
        }
    }
}
