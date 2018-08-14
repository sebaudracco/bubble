package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrf;

final class zzv implements Runnable {
    private final /* synthetic */ zzq zzwt;
    private final /* synthetic */ zzqs zzwx;

    zzv(zzq com_google_android_gms_ads_internal_zzq, zzqs com_google_android_gms_internal_ads_zzqs) {
        this.zzwt = com_google_android_gms_ads_internal_zzq;
        this.zzwx = com_google_android_gms_internal_ads_zzqs;
    }

    public final void run() {
        try {
            ((zzrf) this.zzwt.zzvw.zzadi.get(this.zzwx.getCustomTemplateId())).zzb(this.zzwx);
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
