package com.elephant.data.p037d;

import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import org.json.JSONObject;

public final class C1765c {
    private int f3665a;
    private String f3666b;
    private int f3667c;
    private String f3668d;
    private int f3669e;
    private String f3670f;
    private String f3671g;
    private int f3672h;
    private int f3673i;

    public C1765c(JSONObject jSONObject) {
        this.f3665a = jSONObject.optInt(C1814b.hg, 0);
        this.f3666b = C1816d.m5283a(jSONObject, C1814b.hh, null);
        this.f3668d = C1816d.m5283a(jSONObject, C1814b.hi, null);
        this.f3667c = jSONObject.optInt(C1814b.hj, 0);
        this.f3669e = jSONObject.optInt(C1814b.hk, 1);
        this.f3670f = jSONObject.optString(C1814b.hl, null);
        this.f3672h = jSONObject.optInt(C1814b.hm, 0);
        this.f3673i = jSONObject.optInt(C1814b.hn, 0);
        this.f3671g = jSONObject.optString(C1814b.ho, C1814b.hp);
    }

    public final int m5084a() {
        return this.f3667c;
    }

    public final String m5085b() {
        return this.f3666b;
    }

    public final String m5086c() {
        return this.f3668d;
    }

    public final String m5087d() {
        return this.f3670f;
    }

    public final int m5088e() {
        return this.f3672h;
    }

    public final String m5089f() {
        return this.f3671g;
    }

    public final String toString() {
        return C1814b.hq + C1814b.hr + this.f3665a + C1814b.hs + this.f3666b + '\'' + C1814b.ht + this.f3667c + C1814b.hu + this.f3668d + '\'' + C1814b.hv + this.f3669e + C1814b.hw + this.f3670f + '\'' + C1814b.hx + this.f3671g + '\'' + C1814b.hy + this.f3672h + C1814b.hz + this.f3673i + '}';
    }
}
