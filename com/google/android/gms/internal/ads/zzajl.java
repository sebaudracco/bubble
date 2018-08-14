package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@zzadh
public final class zzajl {
    private final long zzcpe;
    private final List<String> zzcpf = new ArrayList();
    private final List<String> zzcpg = new ArrayList();
    private final Map<String, zzwy> zzcph = new HashMap();
    private String zzcpi;
    private String zzcpj;
    private boolean zzcpk = false;

    public zzajl(String str, long j) {
        int i = 0;
        this.zzcpj = str;
        this.zzcpe = j;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt("status", -1) != 1) {
                    this.zzcpk = false;
                    zzane.zzdk("App settings could not be fetched successfully.");
                    return;
                }
                this.zzcpk = true;
                this.zzcpi = jSONObject.optString("app_id");
                JSONArray optJSONArray = jSONObject.optJSONArray("ad_unit_id_settings");
                if (optJSONArray != null) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                        String optString = jSONObject2.optString("format");
                        CharSequence optString2 = jSONObject2.optString("ad_unit_id");
                        if (!(TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2))) {
                            if ("interstitial".equalsIgnoreCase(optString)) {
                                this.zzcpg.add(optString2);
                            } else if ("rewarded".equalsIgnoreCase(optString)) {
                                jSONObject2 = jSONObject2.optJSONObject("mediation_config");
                                if (jSONObject2 != null) {
                                    this.zzcph.put(optString2, new zzwy(jSONObject2));
                                }
                            }
                        }
                    }
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray("persistable_banner_ad_unit_ids");
                if (optJSONArray2 != null) {
                    while (i < optJSONArray2.length()) {
                        this.zzcpf.add(optJSONArray2.optString(i));
                        i++;
                    }
                }
            } catch (Throwable e) {
                zzane.zzc("Exception occurred while processing app setting json", e);
                zzbv.zzeo().zza(e, "AppSettings.parseAppSettingsJson");
            }
        }
    }

    public final long zzps() {
        return this.zzcpe;
    }

    public final boolean zzpt() {
        return this.zzcpk;
    }

    public final String zzpu() {
        return this.zzcpj;
    }

    public final String zzpv() {
        return this.zzcpi;
    }

    public final Map<String, zzwy> zzpw() {
        return this.zzcph;
    }
}
