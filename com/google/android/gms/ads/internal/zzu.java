package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzoq;

final class zzu implements Runnable {
    private final /* synthetic */ zzq zzwt;
    private final /* synthetic */ zzoq zzww;

    zzu(zzq com_google_android_gms_ads_internal_zzq, zzoq com_google_android_gms_internal_ads_zzoq) {
        this.zzwt = com_google_android_gms_ads_internal_zzq;
        this.zzww = com_google_android_gms_internal_ads_zzoq;
    }

    public final void run() {
        try {
            if (this.zzwt.zzvw.zzadf != null) {
                this.zzwt.zzvw.zzadf.zza(this.zzww);
                this.zzwt.zzb(this.zzww.zzka());
            }
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
