package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final /* synthetic */ class zzacg implements zzase {
    private final JSONObject zzcbg;
    private final zzaqw zzcbh;

    zzacg(zzaqw com_google_android_gms_internal_ads_zzaqw, JSONObject jSONObject) {
        this.zzcbh = com_google_android_gms_internal_ads_zzaqw;
        this.zzcbg = jSONObject;
    }

    public final void zzly() {
        this.zzcbh.zzb("google.afma.nativeAds.renderVideo", this.zzcbg);
    }
}
