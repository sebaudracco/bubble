package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzov;

final class zzs implements Runnable {
    private final /* synthetic */ zzq zzwt;
    private final /* synthetic */ zzov zzwu;

    zzs(zzq com_google_android_gms_ads_internal_zzq, zzov com_google_android_gms_internal_ads_zzov) {
        this.zzwt = com_google_android_gms_ads_internal_zzq;
        this.zzwu = com_google_android_gms_internal_ads_zzov;
    }

    public final void run() {
        try {
            if (this.zzwt.zzvw.zzadg != null) {
                this.zzwt.zzvw.zzadg.zza(this.zzwu);
                this.zzwt.zzb(this.zzwu.zzka());
            }
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
