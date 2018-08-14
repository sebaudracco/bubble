package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

@zzadh
public final class zzafz {
    private final int errorCode;
    private final String type;
    private String url;
    private final String zzcdi;
    private final List<String> zzcjd;
    private final String zzcje;
    private final String zzcjf;
    private final boolean zzcjg;
    private final String zzcjh;
    private final boolean zzcji;
    private final JSONObject zzcjj;

    public zzafz(int i, Map<String, String> map) {
        this.url = (String) map.get("url");
        this.zzcje = (String) map.get("base_uri");
        this.zzcjf = (String) map.get("post_parameters");
        this.zzcjg = parseBoolean((String) map.get("drt_include"));
        this.zzcdi = (String) map.get("request_id");
        this.type = (String) map.get("type");
        this.zzcjd = zzbz((String) map.get("errors"));
        this.errorCode = i;
        this.zzcjh = (String) map.get("fetched_ad");
        this.zzcji = parseBoolean((String) map.get("render_test_ad_label"));
        this.zzcjj = new JSONObject();
    }

    public zzafz(JSONObject jSONObject) {
        int i = 1;
        this.url = jSONObject.optString("url");
        this.zzcje = jSONObject.optString("base_uri");
        this.zzcjf = jSONObject.optString("post_parameters");
        this.zzcjg = parseBoolean(jSONObject.optString("drt_include"));
        this.zzcdi = jSONObject.optString("request_id");
        this.type = jSONObject.optString("type");
        this.zzcjd = zzbz(jSONObject.optString("errors"));
        if (jSONObject.optInt("valid", 0) == 1) {
            i = -2;
        }
        this.errorCode = i;
        this.zzcjh = jSONObject.optString("fetched_ad");
        this.zzcji = jSONObject.optBoolean("render_test_ad_label");
        JSONObject optJSONObject = jSONObject.optJSONObject("preprocessor_flags");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        this.zzcjj = optJSONObject;
    }

    private static boolean parseBoolean(String str) {
        return str != null && (str.equals(SchemaSymbols.ATTVAL_TRUE_1) || str.equals(SchemaSymbols.ATTVAL_TRUE));
    }

    private static List<String> zzbz(String str) {
        return str == null ? null : Arrays.asList(str.split(","));
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final String getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public final List<String> zzoh() {
        return this.zzcjd;
    }

    public final String zzoi() {
        return this.zzcje;
    }

    public final String zzoj() {
        return this.zzcjf;
    }

    public final boolean zzok() {
        return this.zzcjg;
    }

    public final String zzol() {
        return this.zzcdi;
    }

    public final String zzom() {
        return this.zzcjh;
    }

    public final boolean zzon() {
        return this.zzcji;
    }
}
