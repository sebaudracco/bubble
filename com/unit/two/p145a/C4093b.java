package com.unit.two.p145a;

import android.text.TextUtils;
import com.unit.two.p147c.C4096a;
import com.unit.two.p151f.C4145l;
import com.unit.two.p151f.p152a.C4133b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class C4093b {
    private int f9484a;
    private String f9485b;
    private ArrayList f9486c;

    static {
        String str = C4096a.cx;
    }

    public C4093b(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject optJSONObject = jSONObject.optJSONObject(C4096a.cy);
                this.f9484a = optJSONObject.optInt(C4096a.cz);
                this.f9485b = optJSONObject.optString(C4096a.cA);
                int optInt = optJSONObject.optInt(C4096a.cB);
                String str2 = null;
                if (optInt == 0) {
                    str2 = jSONObject.optJSONObject(C4096a.cC).toString();
                } else if (optInt == 1) {
                    str2 = C4145l.m12829b(jSONObject.optString(C4096a.cD));
                } else if (optInt == 2) {
                    str2 = C4133b.m12763b(C4145l.m12829b(jSONObject.optString(C4096a.cE)), C4145l.m12821a());
                }
                jSONObject = new JSONObject(str2);
                jSONObject.optLong(C4096a.cF);
                optJSONObject = jSONObject.optJSONObject(C4096a.cG);
                if (optJSONObject != null) {
                    optJSONObject.optString(C4096a.cH);
                    optJSONObject.optString(C4096a.cI);
                    JSONArray optJSONArray = jSONObject.optJSONArray(C4096a.cJ);
                    this.f9486c = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        this.f9486c.add(new C4092a(optJSONArray.optJSONObject(i)));
                    }
                    optJSONObject = jSONObject.optJSONObject(C4096a.cK);
                    if (optJSONObject != null) {
                        optJSONObject.optInt(C4096a.cL);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public final int m12661a() {
        return this.f9484a;
    }

    public final String m12662b() {
        return this.f9485b;
    }

    public final List m12663c() {
        return this.f9486c;
    }
}
