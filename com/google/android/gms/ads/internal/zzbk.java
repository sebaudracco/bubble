package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrf;

final class zzbk implements Runnable {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ zzqs zzwx;

    zzbk(zzbc com_google_android_gms_ads_internal_zzbc, zzqs com_google_android_gms_internal_ads_zzqs) {
        this.zzaaf = com_google_android_gms_ads_internal_zzbc;
        this.zzwx = com_google_android_gms_internal_ads_zzqs;
    }

    public final void run() {
        try {
            ((zzrf) this.zzaaf.zzvw.zzadi.get(this.zzwx.getCustomTemplateId())).zzb(this.zzwx);
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
