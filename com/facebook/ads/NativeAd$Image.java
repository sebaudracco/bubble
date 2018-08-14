package com.facebook.ads;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.p033n.C2026f;
import org.json.JSONObject;

public class NativeAd$Image {
    private final C2026f f4015a;

    NativeAd$Image(C2026f c2026f) {
        this.f4015a = c2026f;
    }

    public NativeAd$Image(String str, int i, int i2) {
        this.f4015a = new C2026f(str, i, i2);
    }

    @Nullable
    public static NativeAd$Image fromJSONObject(JSONObject jSONObject) {
        C2026f a = C2026f.m6472a(jSONObject);
        return a == null ? null : new NativeAd$Image(a);
    }

    public int getHeight() {
        return this.f4015a.m6475c();
    }

    public String getUrl() {
        return this.f4015a.m6473a();
    }

    public int getWidth() {
        return this.f4015a.m6474b();
    }
}
