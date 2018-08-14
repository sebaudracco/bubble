package com.inmobi.rendering.mraid;

import com.appnext.core.Ad;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONObject;

/* compiled from: OrientationProperties */
public class C3232i {
    private static String f8122e = C3232i.class.getSimpleName();
    public boolean f8123a = true;
    public String f8124b = "none";
    public String f8125c = "right";
    private String f8126d = null;

    public static C3232i m10792a(String str, C3232i c3232i) {
        C3232i c3232i2 = new C3232i();
        c3232i2.f8126d = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            c3232i2.f8124b = jSONObject.optString("forceOrientation", c3232i.f8124b);
            c3232i2.f8123a = jSONObject.optBoolean("allowOrientationChange", c3232i.f8123a);
            c3232i2.f8125c = jSONObject.optString("direction", c3232i.f8125c);
            if (!(c3232i2.f8124b.equals(Ad.ORIENTATION_PORTRAIT) || c3232i2.f8124b.equals(Ad.ORIENTATION_LANDSCAPE))) {
                c3232i2.f8124b = "none";
            }
            if (c3232i2.f8125c.equals("left") || c3232i2.f8125c.equals("right")) {
                return c3232i2;
            }
            c3232i2.f8125c = "right";
            return c3232i2;
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8122e, "Invalid orientation properties string passed.", e);
            return null;
        }
    }

    public String m10793a() {
        return this.f8126d;
    }
}
