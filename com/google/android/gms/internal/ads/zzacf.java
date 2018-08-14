package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final /* synthetic */ class zzacf implements Runnable {
    private final zzaoj zzbns;
    private final zzace zzcbf;
    private final JSONObject zzcbg;

    zzacf(zzace com_google_android_gms_internal_ads_zzace, JSONObject jSONObject, zzaoj com_google_android_gms_internal_ads_zzaoj) {
        this.zzcbf = com_google_android_gms_internal_ads_zzace;
        this.zzcbg = jSONObject;
        this.zzbns = com_google_android_gms_internal_ads_zzaoj;
    }

    public final void run() {
        this.zzcbf.zza(this.zzcbg, this.zzbns);
    }
}
