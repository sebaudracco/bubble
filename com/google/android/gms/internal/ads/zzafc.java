package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzafc implements Runnable {
    private final /* synthetic */ zzafa zzcgj;
    final /* synthetic */ JSONObject zzcgk;
    final /* synthetic */ String zzcgl;

    zzafc(zzafa com_google_android_gms_internal_ads_zzafa, JSONObject jSONObject, String str) {
        this.zzcgj = com_google_android_gms_internal_ads_zzafa;
        this.zzcgk = jSONObject;
        this.zzcgl = str;
    }

    public final void run() {
        zzafa.zza(this.zzcgj, zzafa.zzoe().zzb(null));
        zzafa.zzb(this.zzcgj).zza(new zzafd(this), new zzafe(this));
    }
}
