package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;

final class zzbd implements Runnable {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ zzaji zzwg;

    zzbd(zzbc com_google_android_gms_ads_internal_zzbc, zzaji com_google_android_gms_internal_ads_zzaji) {
        this.zzaaf = com_google_android_gms_ads_internal_zzbc;
        this.zzwg = com_google_android_gms_internal_ads_zzaji;
    }

    public final void run() {
        this.zzaaf.zzb(new zzajh(this.zzwg, null, null, null, null, null, null, null));
    }
}
