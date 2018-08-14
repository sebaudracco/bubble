package com.facebook.ads.internal.p056q.p057a;

import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class C2119j {
    public static String m6798a(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                try {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jSONObject.toString();
    }

    public static String m6799a(JSONObject jSONObject, String str) {
        return C2119j.m6800a(jSONObject, str, null);
    }

    public static String m6800a(JSONObject jSONObject, String str, String str2) {
        String optString = jSONObject.optString(str, str2);
        return "null".equals(optString) ? null : optString;
    }
}
