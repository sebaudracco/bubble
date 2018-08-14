package com.facebook.ads.internal.p058o;

import android.text.TextUtils;
import com.facebook.ads.internal.p058o.C2042f.C2041a;
import com.facebook.ads.internal.p065h.C1987a;
import com.facebook.ads.internal.p065h.C1989c;
import com.facebook.ads.internal.p065h.C1990d;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C2040e {
    private static C2040e f4860a = new C2040e();

    public static synchronized C2040e m6541a() {
        C2040e c2040e;
        synchronized (C2040e.class) {
            c2040e = f4860a;
        }
        return c2040e;
    }

    private C2043g m6542a(JSONObject jSONObject) {
        int i = 0;
        JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
        C1989c c1989c = new C1989c(C1990d.m6291a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config"));
        if (jSONObject2.has("ads")) {
            JSONArray jSONArray = jSONObject2.getJSONArray("ads");
            while (i < jSONArray.length()) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                c1989c.m6287a(new C1987a(jSONObject3.optString("adapter"), jSONObject3.optJSONObject("data"), jSONObject3.optJSONArray("trackers")));
                i++;
            }
        }
        return new C2043g(c1989c, jSONObject.optString("server_request_id"), jSONObject.optString("server_response"), jSONObject.optString("an_validation_uuid"));
    }

    private C2044h m6543b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
            return new C2044h(jSONObject.optString("message", ""), jSONObject.optInt("code", 0), new C1989c(C1990d.m6291a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config")));
        } catch (JSONException e) {
            return m6544c(jSONObject);
        }
    }

    private C2044h m6544c(JSONObject jSONObject) {
        return new C2044h(jSONObject.optString("message", ""), jSONObject.optInt("code", 0), null);
    }

    public C2042f m6545a(String str) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("type");
            Object obj = -1;
            switch (optString.hashCode()) {
                case 96432:
                    if (optString.equals("ads")) {
                        obj = null;
                        break;
                    }
                    break;
                case 96784904:
                    if (optString.equals("error")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return m6542a(jSONObject);
                case 1:
                    return m6543b(jSONObject);
                default:
                    JSONObject optJSONObject = jSONObject.optJSONObject("error");
                    if (optJSONObject != null) {
                        return m6544c(optJSONObject);
                    }
                    break;
            }
        }
        return new C2042f(C2041a.f4861a);
    }
}
