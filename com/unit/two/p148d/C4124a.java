package com.unit.two.p148d;

import com.unit.two.p147c.C4096a;
import org.json.JSONObject;

public final class C4124a {
    private int f9605a = 10;
    private int f9606b = 20;
    private int f9607c = 0;
    private int f9608d = 1;

    static {
        String str = C4096a.bw;
    }

    public C4124a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject(C4096a.bx);
                if (optJSONObject != null) {
                    optJSONObject = optJSONObject.optJSONArray(C4096a.by).optJSONObject(0);
                    if (optJSONObject != null) {
                        this.f9607c = optJSONObject.optInt(C4096a.bz, 0);
                        this.f9605a = optJSONObject.optInt(C4096a.bA, 10);
                        this.f9606b = optJSONObject.optInt(C4096a.bB, 20);
                        this.f9608d = optJSONObject.optInt(C4096a.bC, 1);
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    public final int m12734a() {
        return this.f9605a;
    }

    public final int m12735b() {
        return this.f9606b;
    }

    public final boolean m12736c() {
        return this.f9607c == 1;
    }

    public final boolean m12737d() {
        return this.f9608d == 1;
    }
}
