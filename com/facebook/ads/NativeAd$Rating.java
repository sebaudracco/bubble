package com.facebook.ads;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.p033n.C2027g;
import org.json.JSONObject;

public class NativeAd$Rating {
    private final C2027g f4020a;

    public NativeAd$Rating(double d, double d2) {
        this.f4020a = new C2027g(d, d2);
    }

    NativeAd$Rating(C2027g c2027g) {
        this.f4020a = c2027g;
    }

    @Nullable
    public static NativeAd$Rating fromJSONObject(JSONObject jSONObject) {
        C2027g a = C2027g.m6476a(jSONObject);
        return a == null ? null : new NativeAd$Rating(a);
    }

    public double getScale() {
        return this.f4020a.m6478b();
    }

    public double getValue() {
        return this.f4020a.m6477a();
    }
}
