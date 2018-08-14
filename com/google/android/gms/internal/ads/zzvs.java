package com.google.android.gms.internal.ads;

public final class zzvs extends zzaop<zzwb> {
    private final Object mLock = new Object();
    private final zzvw zzbqq;
    private boolean zzbqr;

    public zzvs(zzvw com_google_android_gms_internal_ads_zzvw) {
        this.zzbqq = com_google_android_gms_internal_ads_zzvw;
    }

    public final void release() {
        synchronized (this.mLock) {
            if (this.zzbqr) {
                return;
            }
            this.zzbqr = true;
            zza(new zzvt(this), new zzaon());
            zza(new zzvu(this), new zzvv(this));
        }
    }
}
