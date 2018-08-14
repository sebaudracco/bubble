package com.google.android.gms.internal.ads;

import java.util.Map;

final /* synthetic */ class zzarm implements Runnable {
    private final Map zzbjl;
    private final zzarl zzdel;

    zzarm(zzarl com_google_android_gms_internal_ads_zzarl, Map map) {
        this.zzdel = com_google_android_gms_internal_ads_zzarl;
        this.zzbjl = map;
    }

    public final void run() {
        this.zzdel.zzo(this.zzbjl);
    }
}
