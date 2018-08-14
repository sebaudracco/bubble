package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzakb;
import org.json.JSONObject;

final class zzx implements Runnable {
    private final /* synthetic */ JSONObject zzbmk;
    private final /* synthetic */ zzw zzbml;

    zzx(zzw com_google_android_gms_ads_internal_gmsg_zzw, JSONObject jSONObject) {
        this.zzbml = com_google_android_gms_ads_internal_gmsg_zzw;
        this.zzbmk = jSONObject;
    }

    public final void run() {
        this.zzbml.zzbmi.zza("fetchHttpRequestCompleted", this.zzbmk);
        zzakb.zzck("Dispatched http response.");
    }
}
