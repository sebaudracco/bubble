package com.facebook.ads.internal.p059a;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class C1879f {
    private final String f4135a;
    private final String f4136b;
    private final String f4137c;
    private final List<String> f4138d;
    private final String f4139e;
    private final String f4140f;

    private C1879f(String str, String str2, String str3, List<String> list, String str4, String str5) {
        this.f4135a = str;
        this.f4136b = str2;
        this.f4137c = str3;
        this.f4138d = list;
        this.f4139e = str4;
        this.f4140f = str5;
    }

    public static C1879f m5650a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("package");
        String optString2 = jSONObject.optString("appsite");
        String optString3 = jSONObject.optString("appsite_url");
        JSONArray optJSONArray = jSONObject.optJSONArray("key_hashes");
        List arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.optString(i));
            }
        }
        return new C1879f(optString, optString2, optString3, arrayList, jSONObject.optString("market_uri"), jSONObject.optString("fallback_url"));
    }

    public String m5651a() {
        return this.f4135a;
    }

    public String m5652b() {
        return this.f4136b;
    }

    public String m5653c() {
        return this.f4137c;
    }
}
