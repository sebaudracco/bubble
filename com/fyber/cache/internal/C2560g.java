package com.fyber.cache.internal;

import com.fyber.cache.internal.C2553a.C2552a;
import com.fyber.utils.FyberLogger;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ConfigurationResponseParser */
public final class C2560g {
    private C2554b f6419a = null;
    private JSONObject f6420b = null;
    private ArrayList<C2561h> f6421c = new ArrayList();

    public static C2560g m8182a(String str) {
        return new C2560g(str);
    }

    private C2560g(String str) {
        FyberLogger.m8451i("ConfigurationResponseParser", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f6420b = jSONObject.getJSONObject("config");
            this.f6419a = C2560g.m8181a(this.f6420b);
            JSONArray jSONArray = jSONObject.getJSONArray("videos");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                this.f6421c.add(new C2561h(jSONObject2.getInt("ad_id"), jSONObject2.getString("url")));
            }
        } catch (JSONException e) {
            FyberLogger.m8448d("ConfigurationResponseParser", e.getLocalizedMessage());
        }
    }

    private static C2554b m8181a(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("id");
        Integer num = null;
        if (jSONObject.has("refresh_interval")) {
            try {
                num = Integer.valueOf(jSONObject.getInt("refresh_interval"));
            } catch (JSONException e) {
            }
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("network_policy");
        JSONObject jSONObject3 = jSONObject2.getJSONObject("wifi");
        jSONObject2 = jSONObject2.getJSONObject("cellular");
        C2556d c2556d = new C2556d(jSONObject3.getInt("max_download_count"));
        return new C2554b().m8148a(C2552a.f6394a, c2556d).m8148a(C2552a.CELLULAR, new C2556d(jSONObject2.getInt("max_download_count"))).m8149a(num).m8150a(string);
    }

    public final C2554b m8183a() {
        return this.f6419a;
    }

    public final ArrayList<C2561h> m8184b() {
        return this.f6421c;
    }
}
