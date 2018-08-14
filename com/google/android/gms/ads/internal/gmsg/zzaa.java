package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzaoj;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONObject;

@zzadh
public final class zzaa implements zzv<Object> {
    @VisibleForTesting
    private final HashMap<String, zzaoj<JSONObject>> zzbmv = new HashMap();

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get("request_id");
        String str2 = (String) map.get("fetched_ad");
        zzane.zzck("Received ad from the cache.");
        zzaoj com_google_android_gms_internal_ads_zzaoj = (zzaoj) this.zzbmv.get(str);
        if (com_google_android_gms_internal_ads_zzaoj == null) {
            zzane.m3427e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        try {
            com_google_android_gms_internal_ads_zzaoj.set(new JSONObject(str2));
        } catch (Throwable e) {
            zzane.zzb("Failed constructing JSON object from value passed from javascript", e);
            com_google_android_gms_internal_ads_zzaoj.set(null);
        } finally {
            this.zzbmv.remove(str);
        }
    }

    public final Future<JSONObject> zzas(String str) {
        Future com_google_android_gms_internal_ads_zzaoj = new zzaoj();
        this.zzbmv.put(str, com_google_android_gms_internal_ads_zzaoj);
        return com_google_android_gms_internal_ads_zzaoj;
    }

    public final void zzat(String str) {
        zzaoj com_google_android_gms_internal_ads_zzaoj = (zzaoj) this.zzbmv.get(str);
        if (com_google_android_gms_internal_ads_zzaoj == null) {
            zzane.m3427e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        if (!com_google_android_gms_internal_ads_zzaoj.isDone()) {
            com_google_android_gms_internal_ads_zzaoj.cancel(true);
        }
        this.zzbmv.remove(str);
    }
}
