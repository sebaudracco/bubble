package com.elephant.data.p046f;

import android.text.TextUtils;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.p049a.C1810b;
import org.json.JSONArray;
import org.json.JSONObject;

final class C1802b {
    boolean f3795a = false;

    private C1802b() {
    }

    public static C1802b m5184a() {
        return new C1802b();
    }

    public static C1802b m5185a(String str) {
        int i = 0;
        C1802b c1802b = new C1802b();
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject optJSONObject = jSONObject.optJSONObject(C1814b.cq);
                Boolean.valueOf(optJSONObject.optInt(C1814b.cr) == 1);
                String optString = jSONObject.optString(C1814b.cs);
                int optInt;
                if (optJSONObject != null) {
                    optInt = optJSONObject.optInt(C1814b.ct);
                    i = optJSONObject.optInt(C1814b.cu);
                } else {
                    optInt = 0;
                }
                if (!TextUtils.isEmpty(optString) && r3 == 200) {
                    if (i == 1) {
                        optString = C1816d.m5280a(optString);
                    }
                    if (i == 2) {
                        optString = C1810b.m5236b(C1816d.m5280a(optString), C1814b.cv);
                    }
                    if (optString != null) {
                        JSONArray optJSONArray = new JSONObject(optString).optJSONObject(C1814b.cw).optJSONArray(C1814b.cx);
                        if (optJSONArray.length() != 0) {
                            c1802b.f3795a = C1814b.cy.equals(optJSONArray.getJSONObject(0).optString(C1814b.cz, C1814b.cA));
                        }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return c1802b;
    }
}
