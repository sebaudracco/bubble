package com.elephant.data.p037d;

import android.text.TextUtils;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import org.json.JSONException;
import org.json.JSONObject;

public final class C1743a {
    private static final String f3589a = C1814b.gt;
    private static final String f3590b = C1814b.gu;
    private static final String f3591c = C1814b.gv;
    private static final String f3592d = C1814b.gw;
    private static final String f3593e = C1814b.gx;
    private String f3594f;
    private String f3595g;
    private String f3596h;
    private String f3597i;
    private long f3598j;
    private boolean f3599k;
    private int f3600l;

    public C1743a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.f3594f = C1816d.m5283a(jSONObject, f3589a, null);
                this.f3597i = C1816d.m5283a(jSONObject, f3592d, null);
                this.f3596h = C1816d.m5283a(jSONObject, f3591c, null);
                this.f3595g = C1816d.m5283a(jSONObject, f3590b, null);
                this.f3598j = jSONObject.optLong(f3593e, 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public C1743a(String str, String str2, String str3, long j, int i) {
        this.f3594f = str;
        this.f3595g = str2;
        this.f3597i = str3;
        this.f3598j = 0;
        this.f3600l = 0;
    }

    public final C1743a m5016a(long j) {
        this.f3598j = j;
        return this;
    }

    public final String m5017a() {
        return this.f3594f;
    }

    public final void m5018a(int i) {
        this.f3600l = i;
    }

    public final void m5019a(String str) {
        this.f3596h = str;
    }

    public final void m5020a(boolean z) {
        this.f3599k = true;
    }

    public final String m5021b() {
        return this.f3595g;
    }

    public final String m5022c() {
        return this.f3596h;
    }

    public final String m5023d() {
        return this.f3597i;
    }

    public final long m5024e() {
        return this.f3598j;
    }

    public final boolean m5025f() {
        return System.currentTimeMillis() > this.f3598j;
    }

    public final boolean m5026g() {
        return this.f3599k;
    }

    public final int m5027h() {
        return this.f3600l;
    }

    public final String m5028i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f3589a, this.f3594f);
            jSONObject.put(f3592d, this.f3597i);
            jSONObject.put(f3591c, this.f3596h);
            jSONObject.put(f3590b, this.f3595g);
            jSONObject.put(f3593e, this.f3598j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
