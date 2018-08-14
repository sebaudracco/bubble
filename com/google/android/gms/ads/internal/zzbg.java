package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzoo;

final class zzbg implements Runnable {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ zzoo zzwv;

    zzbg(zzbc com_google_android_gms_ads_internal_zzbc, zzoo com_google_android_gms_internal_ads_zzoo) {
        this.zzaaf = com_google_android_gms_ads_internal_zzbc;
        this.zzwv = com_google_android_gms_internal_ads_zzoo;
    }

    public final void run() {
        try {
            if (this.zzaaf.zzvw.zzade != null) {
                this.zzaaf.zzvw.zzade.zza(this.zzwv);
                this.zzaaf.zzb(this.zzwv.zzka());
            }
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
