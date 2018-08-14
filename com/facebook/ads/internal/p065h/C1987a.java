package com.facebook.ads.internal.p065h;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class C1987a {
    private final String f4641a;
    private final JSONObject f4642b;
    private final Map<C1991e, List<String>> f4643c = new HashMap();

    public C1987a(String str, JSONObject jSONObject, @Nullable JSONArray jSONArray) {
        this.f4641a = str;
        this.f4642b = jSONObject;
        if (jSONArray != null && jSONArray.length() != 0) {
            int i;
            for (Object put : C1991e.values()) {
                this.f4643c.put(put, new LinkedList());
            }
            for (i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("type");
                    CharSequence string2 = jSONObject2.getString("url");
                    C1991e valueOf = C1991e.valueOf(string.toUpperCase(Locale.US));
                    if (!(valueOf == null || TextUtils.isEmpty(string2))) {
                        ((List) this.f4643c.get(valueOf)).add(string2);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public String m6280a() {
        return this.f4641a;
    }

    public List<String> m6281a(C1991e c1991e) {
        return (List) this.f4643c.get(c1991e);
    }

    public JSONObject m6282b() {
        return this.f4642b;
    }
}
