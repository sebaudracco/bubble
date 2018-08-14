package com.google.android.gms.internal.ads;

final class zzvo implements zzaoo<zzuu> {
    private final /* synthetic */ zzvf zzbqk;
    private final /* synthetic */ zzvw zzbqn;

    zzvo(zzvf com_google_android_gms_internal_ads_zzvf, zzvw com_google_android_gms_internal_ads_zzvw) {
        this.zzbqk = com_google_android_gms_internal_ads_zzvf;
        this.zzbqn = com_google_android_gms_internal_ads_zzvw;
    }

    public final /* synthetic */ void zze(Object obj) {
        synchronized (zzvf.zza(this.zzbqk)) {
            zzvf.zza(this.zzbqk, 0);
            if (!(zzvf.zzb(this.zzbqk) == null || this.zzbqn == zzvf.zzb(this.zzbqk))) {
                zzakb.v("New JS engine is loaded, marking previous one as destroyable.");
                zzvf.zzb(this.zzbqk).zzmb();
            }
            zzvf.zza(this.zzbqk, this.zzbqn);
        }
    }
}
