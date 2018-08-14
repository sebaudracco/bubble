package com.facebook.ads.internal.adapters;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p052j.C2000c;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p059a.C1875c;
import com.facebook.ads.internal.p059a.C1877d;
import com.facebook.ads.internal.p059a.C1877d.C1876a;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C1931r implements C1876a {
    private final String f4442a;
    private final String f4443b;
    private final C1875c f4444c;
    private final Collection<String> f4445d;
    private final Map<String, String> f4446e;
    private final String f4447f;
    private final int f4448g;
    private final int f4449h;
    private final int f4450i;
    private final String f4451j;

    private C1931r(String str, String str2, C1875c c1875c, Collection<String> collection, Map<String, String> map, String str3, int i, int i2, int i3, String str4) {
        this.f4442a = str;
        this.f4443b = str2;
        this.f4444c = c1875c;
        this.f4445d = collection;
        this.f4446e = map;
        this.f4447f = str3;
        this.f4448g = i;
        this.f4449h = i2;
        this.f4450i = i3;
        this.f4451j = str4;
    }

    public static C1931r m6041a(Bundle bundle) {
        return new C1931r(C2000c.m6323a(bundle.getByteArray("markup")), null, C1875c.NONE, null, null, bundle.getString("request_id"), bundle.getInt("viewability_check_initial_delay"), bundle.getInt("viewability_check_interval"), bundle.getInt("skip_after_seconds", 0), bundle.getString("ct"));
    }

    public static C1931r m6042a(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        JSONArray jSONArray;
        String optString = jSONObject.optString("markup");
        String optString2 = jSONObject.optString("activation_command");
        String optString3 = jSONObject.optString("request_id");
        String a = C2119j.m6799a(jSONObject, "ct");
        C1875c a2 = C1875c.m5634a(jSONObject.optString("invalidation_behavior"));
        try {
            jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
        } catch (JSONException e) {
            e.printStackTrace();
            jSONArray = null;
        }
        Collection a3 = C1877d.m5638a(jSONArray);
        JSONObject optJSONObject = jSONObject.optJSONObject("metadata");
        Map hashMap = new HashMap();
        if (optJSONObject != null) {
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, optJSONObject.optString(str));
            }
        }
        int i2 = 1000;
        int parseInt = hashMap.containsKey("viewability_check_initial_delay") ? Integer.parseInt((String) hashMap.get("viewability_check_initial_delay")) : 0;
        if (hashMap.containsKey("viewability_check_interval")) {
            i2 = Integer.parseInt((String) hashMap.get("viewability_check_interval"));
        }
        if (hashMap.containsKey("skip_after_seconds")) {
            i = Integer.parseInt((String) hashMap.get("skip_after_seconds"));
        }
        return new C1931r(optString, optString2, a2, a3, hashMap, optString3, parseInt, i2, i, a);
    }

    public static C1931r m6043b(Intent intent) {
        return new C1931r(C2000c.m6323a(intent.getByteArrayExtra("markup")), intent.getStringExtra("activation_command"), C1875c.NONE, null, null, intent.getStringExtra("request_id"), intent.getIntExtra("viewability_check_initial_delay", 0), intent.getIntExtra("viewability_check_interval", 1000), intent.getIntExtra(AudienceNetworkActivity.SKIP_DELAY_SECONDS_KEY, 0), intent.getStringExtra("ct"));
    }

    public C1875c mo3690a() {
        return this.f4444c;
    }

    public void m6045a(Intent intent) {
        intent.putExtra("markup", C2000c.m6324a(this.f4442a));
        intent.putExtra("activation_command", this.f4443b);
        intent.putExtra("request_id", this.f4447f);
        intent.putExtra("viewability_check_initial_delay", this.f4448g);
        intent.putExtra("viewability_check_interval", this.f4449h);
        intent.putExtra(AudienceNetworkActivity.SKIP_DELAY_SECONDS_KEY, this.f4450i);
        intent.putExtra("ct", this.f4451j);
    }

    public Collection<String> mo3692b() {
        return this.f4445d;
    }

    public String mo3642c() {
        return this.f4451j;
    }

    public String m6048d() {
        return this.f4442a;
    }

    public String m6049e() {
        return this.f4443b;
    }

    public Map<String, String> m6050f() {
        return this.f4446e;
    }

    public String m6051g() {
        return this.f4447f;
    }

    public int m6052h() {
        return this.f4448g;
    }

    public int m6053i() {
        return this.f4449h;
    }

    public Bundle m6054j() {
        Bundle bundle = new Bundle();
        bundle.putByteArray("markup", C2000c.m6324a(this.f4442a));
        bundle.putString("request_id", this.f4447f);
        bundle.putInt("viewability_check_initial_delay", this.f4448g);
        bundle.putInt("viewability_check_interval", this.f4449h);
        bundle.putInt("skip_after_seconds", this.f4450i);
        bundle.putString("ct", this.f4451j);
        return bundle;
    }
}
