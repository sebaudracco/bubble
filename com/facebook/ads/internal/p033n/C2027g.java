package com.facebook.ads.internal.p033n;

import android.support.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import org.json.JSONObject;

public class C2027g {
    private final double f4805a;
    private final double f4806b;

    public C2027g(double d, double d2) {
        this.f4805a = d;
        this.f4806b = d2;
    }

    @Nullable
    public static C2027g m6476a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        double optDouble = jSONObject.optDouble(FirebaseAnalytics$Param.VALUE, 0.0d);
        double optDouble2 = jSONObject.optDouble("scale", 0.0d);
        return (optDouble == 0.0d || optDouble2 == 0.0d) ? null : new C2027g(optDouble, optDouble2);
    }

    public double m6477a() {
        return this.f4805a;
    }

    public double m6478b() {
        return this.f4806b;
    }
}
