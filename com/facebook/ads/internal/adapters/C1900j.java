package com.facebook.ads.internal.adapters;

import android.graphics.Color;
import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONObject;

public class C1900j implements Serializable {
    public static final int f4265a = Color.parseColor("#90949c");
    public static final int f4266b = Color.parseColor("#4b4f56");
    public static final int f4267c = Color.parseColor("#f6f7f9");
    public static final int f4268d = Color.parseColor("#ff4080ff");
    public static final int f4269e = Color.parseColor("#23272F");
    public static final int f4270f = Color.parseColor("#ff4080ff");
    private static final long serialVersionUID = 8946536326456653736L;
    private int f4271g = f4265a;
    private int f4272h = f4266b;
    private int f4273i = -16777216;
    private int f4274j = f4267c;
    private int f4275k = f4268d;
    private int f4276l = -1;
    private int f4277m = -16777216;

    public static C1900j m5828a(JSONObject jSONObject) {
        C1900j c1900j = new C1900j();
        if (jSONObject != null) {
            Object optString = jSONObject.optString("accent_color");
            Object optString2 = jSONObject.optString("body_color");
            Object optString3 = jSONObject.optString("subtitle_color");
            Object optString4 = jSONObject.optString("bg_color");
            Object optString5 = jSONObject.optString("cta_color");
            Object optString6 = jSONObject.optString("cta_text_color");
            Object optString7 = jSONObject.optString("title_color");
            if (!TextUtils.isEmpty(optString)) {
                c1900j.f4271g = Color.parseColor(optString);
            }
            if (!TextUtils.isEmpty(optString2)) {
                c1900j.f4272h = Color.parseColor(optString2);
            }
            if (!TextUtils.isEmpty(optString3)) {
                c1900j.f4273i = Color.parseColor(optString3);
            }
            if (!TextUtils.isEmpty(optString4)) {
                c1900j.f4274j = Color.parseColor(optString4);
            }
            if (!TextUtils.isEmpty(optString5)) {
                c1900j.f4275k = Color.parseColor(optString5);
            }
            if (!TextUtils.isEmpty(optString6)) {
                c1900j.f4276l = Color.parseColor(optString6);
            }
            if (!TextUtils.isEmpty(optString7)) {
                c1900j.f4277m = Color.parseColor(optString7);
            }
        }
        return c1900j;
    }

    public int m5829a(boolean z) {
        return z ? -1 : this.f4271g;
    }

    public int m5830b(boolean z) {
        return z ? -1 : this.f4272h;
    }

    public int m5831c(boolean z) {
        return z ? -1 : this.f4273i;
    }

    public int m5832d(boolean z) {
        return z ? f4269e : this.f4274j;
    }

    public int m5833e(boolean z) {
        return z ? -1 : this.f4275k;
    }

    public int m5834f(boolean z) {
        return z ? f4270f : this.f4276l;
    }

    public int m5835g(boolean z) {
        return z ? -1 : this.f4277m;
    }
}
