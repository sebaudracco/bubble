package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzafp implements Runnable {
    private final /* synthetic */ zzaef zzchk;
    private final /* synthetic */ zzaeq zzchl;
    private final /* synthetic */ zzafn zzchm;

    zzafp(zzafn com_google_android_gms_internal_ads_zzafn, zzaef com_google_android_gms_internal_ads_zzaef, zzaeq com_google_android_gms_internal_ads_zzaeq) {
        this.zzchm = com_google_android_gms_internal_ads_zzafn;
        this.zzchk = com_google_android_gms_internal_ads_zzaef;
        this.zzchl = com_google_android_gms_internal_ads_zzaeq;
    }

    public final void run() {
        zzaej zzb;
        try {
            zzb = this.zzchm.zzb(this.zzchk);
        } catch (Throwable e) {
            zzbv.zzeo().zza(e, "AdRequestServiceImpl.loadAdAsync");
            zzakb.zzc("Could not fetch ad response due to an Exception.", e);
            zzb = null;
        }
        if (zzb == null) {
            zzb = new zzaej(0);
        }
        try {
            this.zzchl.zza(zzb);
        } catch (Throwable e2) {
            zzakb.zzc("Fail to forward ad response.", e2);
        }
    }
}
