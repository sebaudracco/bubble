package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzapv {
    private final boolean zzczu;
    private final int zzczv;
    private final int zzczw;
    private final int zzczx;
    private final String zzczy;
    private final int zzczz;
    private final int zzdaa;
    private final int zzdab;
    private final boolean zzdac;

    public zzapv(String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
            }
        } else {
            jSONObject = null;
        }
        jSONObject2 = jSONObject;
        this.zzczu = zza(jSONObject2, "aggressive_media_codec_release", zznk.zzavl);
        this.zzczv = zzb(jSONObject2, "byte_buffer_precache_limit", zznk.zzauv);
        this.zzczw = zzb(jSONObject2, "exo_cache_buffer_size", zznk.zzauz);
        this.zzczx = zzb(jSONObject2, "exo_connect_timeout_millis", zznk.zzaur);
        this.zzczy = zzc(jSONObject2, "exo_player_version", zznk.zzauq);
        this.zzczz = zzb(jSONObject2, "exo_read_timeout_millis", zznk.zzaus);
        this.zzdaa = zzb(jSONObject2, "load_check_interval_bytes", zznk.zzaut);
        this.zzdab = zzb(jSONObject2, "player_precache_limit", zznk.zzauu);
        this.zzdac = zza(jSONObject2, "use_cache_data_source", zznk.zzbdr);
    }

    private static boolean zza(JSONObject jSONObject, String str, zzna<Boolean> com_google_android_gms_internal_ads_zzna_java_lang_Boolean) {
        if (jSONObject != null) {
            try {
                return jSONObject.getBoolean(str);
            } catch (JSONException e) {
            }
        }
        return ((Boolean) zzkb.zzik().zzd(com_google_android_gms_internal_ads_zzna_java_lang_Boolean)).booleanValue();
    }

    private static int zzb(JSONObject jSONObject, String str, zzna<Integer> com_google_android_gms_internal_ads_zzna_java_lang_Integer) {
        if (jSONObject != null) {
            try {
                return jSONObject.getInt(str);
            } catch (JSONException e) {
            }
        }
        return ((Integer) zzkb.zzik().zzd(com_google_android_gms_internal_ads_zzna_java_lang_Integer)).intValue();
    }

    private static String zzc(JSONObject jSONObject, String str, zzna<String> com_google_android_gms_internal_ads_zzna_java_lang_String) {
        if (jSONObject != null) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException e) {
            }
        }
        return (String) zzkb.zzik().zzd(com_google_android_gms_internal_ads_zzna_java_lang_String);
    }
}
