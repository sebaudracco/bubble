package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONObject;

final /* synthetic */ class zzph implements zzasd {
    private final zzpg zzbjk;
    private final Map zzbjl;
    private final zzacm zzbjm;

    zzph(zzpg com_google_android_gms_internal_ads_zzpg, Map map, zzacm com_google_android_gms_internal_ads_zzacm) {
        this.zzbjk = com_google_android_gms_internal_ads_zzpg;
        this.zzbjl = map;
        this.zzbjm = com_google_android_gms_internal_ads_zzacm;
    }

    public final void zze(boolean z) {
        zzpg com_google_android_gms_internal_ads_zzpg = this.zzbjk;
        Map map = this.zzbjl;
        zzacm com_google_android_gms_internal_ads_zzacm = this.zzbjm;
        com_google_android_gms_internal_ads_zzpg.zzbjj.zzbjh = (String) map.get("id");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("messageType", "htmlLoaded");
            jSONObject.put("id", com_google_android_gms_internal_ads_zzpg.zzbjj.zzbjh);
            com_google_android_gms_internal_ads_zzacm.zza("sendMessageToNativeJs", jSONObject);
        } catch (Throwable e) {
            zzakb.zzb("Unable to dispatch sendMessageToNativeJs event", e);
        }
    }
}
