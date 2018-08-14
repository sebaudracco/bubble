package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONObject;

/* compiled from: ResizeProperties */
public class C3233j {
    private static final String f8127g = C3233j.class.getSimpleName();
    String f8128a = "top-right";
    int f8129b;
    int f8130c;
    int f8131d = 0;
    int f8132e = 0;
    boolean f8133f = true;

    public static C3233j m10794a(String str, C3233j c3233j) {
        C3233j c3233j2 = new C3233j();
        try {
            JSONObject jSONObject = new JSONObject(str);
            c3233j2.f8129b = jSONObject.getInt("width");
            c3233j2.f8130c = jSONObject.getInt("height");
            c3233j2.f8131d = jSONObject.getInt("offsetX");
            c3233j2.f8132e = jSONObject.getInt("offsetY");
            if (c3233j == null) {
                return c3233j2;
            }
            c3233j2.f8128a = jSONObject.optString("customClosePosition", c3233j.f8128a);
            c3233j2.f8133f = jSONObject.optBoolean("allowOffscreen", c3233j.f8133f);
            return c3233j2;
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8127g, "Invalid resize properties string passed.", e);
            return null;
        }
    }

    public String m10795a() {
        String str = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("width", this.f8129b);
            jSONObject.put("height", this.f8130c);
            jSONObject.put("customClosePosition", this.f8128a);
            jSONObject.put("offsetX", this.f8131d);
            jSONObject.put("offsetY", this.f8132e);
            jSONObject.put("allowOffscreen", this.f8133f);
            str = jSONObject.toString();
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8127g, "Invalid resize properties string passed.", e);
        }
        return str;
    }
}
