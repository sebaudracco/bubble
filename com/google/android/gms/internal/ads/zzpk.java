package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;
import org.json.JSONObject;

final class zzpk implements zzv<Object> {
    private final /* synthetic */ zzacm zzbji;
    private final /* synthetic */ zzpf zzbjj;

    zzpk(zzpf com_google_android_gms_internal_ads_zzpf, zzacm com_google_android_gms_internal_ads_zzacm) {
        this.zzbjj = com_google_android_gms_internal_ads_zzpf;
        this.zzbji = com_google_android_gms_internal_ads_zzacm;
    }

    public final void zza(Object obj, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : map.keySet()) {
                jSONObject.put(str, map.get(str));
            }
            jSONObject.put("id", this.zzbjj.zzbjh);
            this.zzbji.zza("sendMessageToNativeJs", jSONObject);
        } catch (Throwable e) {
            zzakb.zzb("Unable to dispatch sendMessageToNativeJs event", e);
        }
    }
}
