package com.google.android.gms.internal.ads;

final class zzvp implements zzaom {
    private final /* synthetic */ zzvf zzbqk;
    private final /* synthetic */ zzvw zzbqn;

    zzvp(zzvf com_google_android_gms_internal_ads_zzvf, zzvw com_google_android_gms_internal_ads_zzvw) {
        this.zzbqk = com_google_android_gms_internal_ads_zzvf;
        this.zzbqn = com_google_android_gms_internal_ads_zzvw;
    }

    public final void run() {
        synchronized (zzvf.zza(this.zzbqk)) {
            zzvf.zza(this.zzbqk, 1);
            zzakb.v("Failed loading new engine. Marking new engine destroyable.");
            this.zzbqn.zzmb();
        }
    }
}
