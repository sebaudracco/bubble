package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzue;
import java.util.Map;
import org.json.JSONObject;

final class zzw implements Runnable {
    private final /* synthetic */ Map zzbmh;
    final /* synthetic */ zzue zzbmi;
    private final /* synthetic */ HttpClient zzbmj;

    zzw(HttpClient httpClient, Map map, zzue com_google_android_gms_internal_ads_zzue) {
        this.zzbmj = httpClient;
        this.zzbmh = map;
        this.zzbmi = com_google_android_gms_internal_ads_zzue;
    }

    public final void run() {
        zzakb.zzck("Received Http request.");
        try {
            JSONObject send = this.zzbmj.send(new JSONObject((String) this.zzbmh.get("http_request")));
            if (send == null) {
                zzakb.e("Response should not be null.");
            } else {
                zzakk.zzcrm.post(new zzx(this, send));
            }
        } catch (Throwable e) {
            zzakb.zzb("Error converting request to json.", e);
        }
    }
}
