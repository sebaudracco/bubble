package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzoo;

final class zzt implements Runnable {
    private final /* synthetic */ zzq zzwt;
    private final /* synthetic */ zzoo zzwv;

    zzt(zzq com_google_android_gms_ads_internal_zzq, zzoo com_google_android_gms_internal_ads_zzoo) {
        this.zzwt = com_google_android_gms_ads_internal_zzq;
        this.zzwv = com_google_android_gms_internal_ads_zzoo;
    }

    public final void run() {
        try {
            if (this.zzwt.zzvw.zzade != null) {
                this.zzwt.zzvw.zzade.zza(this.zzwv);
                this.zzwt.zzb(this.zzwv.zzka());
            }
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
