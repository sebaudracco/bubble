package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzov;

final class zzbh implements Runnable {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ zzov zzwu;

    zzbh(zzbc com_google_android_gms_ads_internal_zzbc, zzov com_google_android_gms_internal_ads_zzov) {
        this.zzaaf = com_google_android_gms_ads_internal_zzbc;
        this.zzwu = com_google_android_gms_internal_ads_zzov;
    }

    public final void run() {
        try {
            if (this.zzaaf.zzvw.zzadg != null) {
                this.zzaaf.zzvw.zzadg.zza(this.zzwu);
                this.zzaaf.zzb(this.zzwu.zzka());
            }
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
