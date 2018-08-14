package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzwx {
    public final String zzbrr;
    public final String zzbrs;
    public final List<String> zzbrt;
    public final String zzbru;
    public final String zzbrv;
    public final List<String> zzbrw;
    public final List<String> zzbrx;
    public final List<String> zzbry;
    public final List<String> zzbrz;
    public final List<String> zzbsa;
    public final String zzbsb;
    public final List<String> zzbsc;
    public final List<String> zzbsd;
    public final List<String> zzbse;
    @Nullable
    public final String zzbsf;
    @Nullable
    public final String zzbsg;
    public final String zzbsh;
    @Nullable
    public final List<String> zzbsi;
    public final String zzbsj;
    @Nullable
    private final String zzbsk;
    public final long zzbsl;

    public zzwx(String str, String str2, List<String> list, String str3, String str4, List<String> list2, List<String> list3, List<String> list4, List<String> list5, String str5, String str6, List<String> list6, List<String> list7, List<String> list8, String str7, String str8, String str9, List<String> list9, String str10, List<String> list10, String str11, long j) {
        this.zzbrr = str;
        this.zzbrs = null;
        this.zzbrt = list;
        this.zzbru = null;
        this.zzbrv = null;
        this.zzbrw = list2;
        this.zzbrx = list3;
        this.zzbry = list4;
        this.zzbrz = list5;
        this.zzbsb = str5;
        this.zzbsc = list6;
        this.zzbsd = list7;
        this.zzbse = list8;
        this.zzbsf = null;
        this.zzbsg = null;
        this.zzbsh = null;
        this.zzbsi = null;
        this.zzbsj = null;
        this.zzbsa = list10;
        this.zzbsk = null;
        this.zzbsl = -1;
    }

    public zzwx(JSONObject jSONObject) throws JSONException {
        List zza;
        this.zzbrs = jSONObject.optString("id");
        JSONArray jSONArray = jSONObject.getJSONArray("adapters");
        List arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        this.zzbrt = Collections.unmodifiableList(arrayList);
        this.zzbru = jSONObject.optString("allocation_id", null);
        zzbv.zzfd();
        this.zzbrw = zzxg.zza(jSONObject, "clickurl");
        zzbv.zzfd();
        this.zzbrx = zzxg.zza(jSONObject, "imp_urls");
        zzbv.zzfd();
        this.zzbry = zzxg.zza(jSONObject, "downloaded_imp_urls");
        zzbv.zzfd();
        this.zzbsa = zzxg.zza(jSONObject, "fill_urls");
        zzbv.zzfd();
        this.zzbsc = zzxg.zza(jSONObject, "video_start_urls");
        zzbv.zzfd();
        this.zzbse = zzxg.zza(jSONObject, "video_complete_urls");
        zzbv.zzfd();
        this.zzbsd = ((Boolean) zzkb.zzik().zzd(zznk.zzaxv)).booleanValue() ? zzxg.zza(jSONObject, "video_reward_urls") : this.zzbse;
        JSONObject optJSONObject = jSONObject.optJSONObject("ad");
        if (optJSONObject != null) {
            zzbv.zzfd();
            zza = zzxg.zza(optJSONObject, "manual_impression_urls");
        } else {
            zza = null;
        }
        this.zzbrz = zza;
        this.zzbrr = optJSONObject != null ? optJSONObject.toString() : null;
        optJSONObject = jSONObject.optJSONObject("data");
        this.zzbsb = optJSONObject != null ? optJSONObject.toString() : null;
        this.zzbrv = optJSONObject != null ? optJSONObject.optString("class_name") : null;
        this.zzbsf = jSONObject.optString("html_template", null);
        this.zzbsg = jSONObject.optString("ad_base_url", null);
        JSONObject optJSONObject2 = jSONObject.optJSONObject("assets");
        this.zzbsh = optJSONObject2 != null ? optJSONObject2.toString() : null;
        zzbv.zzfd();
        this.zzbsi = zzxg.zza(jSONObject, "template_ids");
        optJSONObject2 = jSONObject.optJSONObject("ad_loader_options");
        this.zzbsj = optJSONObject2 != null ? optJSONObject2.toString() : null;
        this.zzbsk = jSONObject.optString("response_type", null);
        this.zzbsl = jSONObject.optLong("ad_network_timeout_millis", -1);
    }

    public final boolean zzmf() {
        return "banner".equalsIgnoreCase(this.zzbsk);
    }

    public final boolean zzmg() {
        return "native".equalsIgnoreCase(this.zzbsk);
    }
}
