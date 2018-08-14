package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;

public final class zzvw extends zzaop<zzuu> {
    private final Object mLock = new Object();
    private zzalo<zzuu> zzbpz;
    private boolean zzbqt;
    private int zzbqu;

    public zzvw(zzalo<zzuu> com_google_android_gms_internal_ads_zzalo_com_google_android_gms_internal_ads_zzuu) {
        this.zzbpz = com_google_android_gms_internal_ads_zzalo_com_google_android_gms_internal_ads_zzuu;
        this.zzbqt = false;
        this.zzbqu = 0;
    }

    private final void zzmc() {
        synchronized (this.mLock) {
            Preconditions.checkState(this.zzbqu >= 0);
            if (this.zzbqt && this.zzbqu == 0) {
                zzakb.v("No reference is left (including root). Cleaning up engine.");
                zza(new zzvz(this), new zzaon());
            } else {
                zzakb.v("There are still references to the engine. Not destroying.");
            }
        }
    }

    public final zzvs zzlz() {
        zzvs com_google_android_gms_internal_ads_zzvs = new zzvs(this);
        synchronized (this.mLock) {
            zza(new zzvx(this, com_google_android_gms_internal_ads_zzvs), new zzvy(this, com_google_android_gms_internal_ads_zzvs));
            Preconditions.checkState(this.zzbqu >= 0);
            this.zzbqu++;
        }
        return com_google_android_gms_internal_ads_zzvs;
    }

    protected final void zzma() {
        synchronized (this.mLock) {
            Preconditions.checkState(this.zzbqu > 0);
            zzakb.v("Releasing 1 reference for JS Engine");
            this.zzbqu--;
            zzmc();
        }
    }

    public final void zzmb() {
        boolean z = true;
        synchronized (this.mLock) {
            if (this.zzbqu < 0) {
                z = false;
            }
            Preconditions.checkState(z);
            zzakb.v("Releasing root reference. JS Engine will be destroyed once other references are released.");
            this.zzbqt = true;
            zzmc();
        }
    }
}
