package com.unit.two.p145a;

import com.unit.two.p147c.C4096a;
import com.unit.two.p151f.C4134a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class C4092a {
    private C4134a f9478a;
    private HashMap f9479b;
    private int f9480c;
    private int f9481d;
    private int f9482e;
    private int f9483f;

    static {
        String str = C4096a.cM;
    }

    public C4092a(JSONObject jSONObject) {
        try {
            jSONObject.optInt(C4096a.cN);
            jSONObject.optInt(C4096a.cO);
            jSONObject.optInt(C4096a.cP);
            jSONObject.optLong(C4096a.cQ);
            jSONObject.optInt(C4096a.cR);
            this.f9478a = new C4134a(jSONObject.optJSONObject(C4096a.cS));
            this.f9479b = new HashMap();
            JSONObject optJSONObject = jSONObject.optJSONObject(C4096a.cT);
            if (optJSONObject != null) {
                Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    JSONArray optJSONArray = optJSONObject.optJSONArray(str);
                    List arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        arrayList.add(optJSONArray.optString(i));
                    }
                    this.f9479b.put(str, arrayList);
                }
            }
            this.f9480c = jSONObject.optInt(C4096a.cU);
            this.f9481d = jSONObject.optInt(C4096a.cV);
            this.f9482e = jSONObject.optInt(C4096a.cW);
            this.f9483f = jSONObject.optInt(C4096a.cX);
        } catch (Exception e) {
        }
    }

    public final C4134a m12656a() {
        return this.f9478a;
    }

    public final int m12657b() {
        return this.f9480c;
    }

    public final int m12658c() {
        return this.f9481d;
    }

    public final int m12659d() {
        return this.f9482e;
    }

    public final int m12660e() {
        return this.f9483f;
    }
}
