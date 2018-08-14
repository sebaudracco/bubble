package com.facebook.ads.internal.p033n;

import android.support.annotation.Nullable;
import org.json.JSONObject;

public class C2026f {
    private final String f4802a;
    private final int f4803b;
    private final int f4804c;

    public C2026f(String str, int i, int i2) {
        this.f4802a = str;
        this.f4803b = i;
        this.f4804c = i2;
    }

    @Nullable
    public static C2026f m6472a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("url");
        return optString != null ? new C2026f(optString, jSONObject.optInt("width", 0), jSONObject.optInt("height", 0)) : null;
    }

    public String m6473a() {
        return this.f4802a;
    }

    public int m6474b() {
        return this.f4803b;
    }

    public int m6475c() {
        return this.f4804c;
    }
}
