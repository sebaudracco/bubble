package com.unit.two.p147c;

import android.text.TextUtils;
import com.unit.two.p151f.C4145l;
import com.unit.two.p151f.p152a.C4133b;
import org.json.JSONArray;
import org.json.JSONObject;

final class C4100e {
    boolean f9554a = false;

    private C4100e() {
    }

    public static C4100e m12683a() {
        return new C4100e();
    }

    public static C4100e m12684a(String str) {
        int i = 0;
        C4100e c4100e = new C4100e();
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject optJSONObject = jSONObject.optJSONObject(C4096a.dt);
                Boolean.valueOf(optJSONObject.optInt(C4096a.du) == 1);
                String optString = jSONObject.optString(C4096a.dv);
                int optInt;
                if (optJSONObject != null) {
                    optInt = optJSONObject.optInt(C4096a.dw);
                    i = optJSONObject.optInt(C4096a.dx);
                } else {
                    optInt = 0;
                }
                if (!TextUtils.isEmpty(optString) && r3 == 200) {
                    if (i == 1) {
                        optString = C4145l.m12829b(optString);
                    }
                    if (i == 2) {
                        optString = C4133b.m12763b(C4145l.m12829b(optString), C4096a.dy);
                    }
                    if (optString != null) {
                        JSONArray optJSONArray = new JSONObject(optString).optJSONObject(C4096a.dz).optJSONArray(C4096a.dA);
                        if (optJSONArray.length() != 0) {
                            c4100e.f9554a = C4096a.dB.equals(optJSONArray.getJSONObject(0).optString(C4096a.dC, C4096a.dD));
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
        return c4100e;
    }
}
