package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzos;
import com.google.android.gms.internal.ads.zzrf;

final class zzbj implements Runnable {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ String zzaal;
    private final /* synthetic */ zzajh zzaam;

    zzbj(zzbc com_google_android_gms_ads_internal_zzbc, String str, zzajh com_google_android_gms_internal_ads_zzajh) {
        this.zzaaf = com_google_android_gms_ads_internal_zzbc;
        this.zzaal = str;
        this.zzaam = com_google_android_gms_internal_ads_zzajh;
    }

    public final void run() {
        try {
            ((zzrf) this.zzaaf.zzvw.zzadi.get(this.zzaal)).zzb((zzos) this.zzaam.zzcoj);
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
