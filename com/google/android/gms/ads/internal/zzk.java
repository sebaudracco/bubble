package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;

final class zzk implements Runnable {
    private final /* synthetic */ zzaji zzwg;
    private final /* synthetic */ zzi zzwm;

    zzk(zzi com_google_android_gms_ads_internal_zzi, zzaji com_google_android_gms_internal_ads_zzaji) {
        this.zzwm = com_google_android_gms_ads_internal_zzi;
        this.zzwg = com_google_android_gms_internal_ads_zzaji;
    }

    public final void run() {
        this.zzwm.zzb(new zzajh(this.zzwg, null, null, null, null, null, null, null));
    }
}
