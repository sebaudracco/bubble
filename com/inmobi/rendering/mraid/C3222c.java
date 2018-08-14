package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ExpandProperties */
public class C3222c {
    private static final String f8082c = C3222c.class.getSimpleName();
    public int f8083a = DisplayInfo.m10420a().m10439b();
    public int f8084b = DisplayInfo.m10420a().m10438a();
    private boolean f8085d = false;
    private boolean f8086e;
    private boolean f8087f = true;
    private String f8088g;

    public C3222c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", this.f8083a);
            jSONObject.put("height", this.f8084b);
            jSONObject.put("useCustomClose", this.f8085d);
            jSONObject.put("isModal", this.f8087f);
        } catch (JSONException e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8082c, "Exception in composing ExpandProperties: " + e.getMessage());
        }
        this.f8088g = jSONObject.toString();
    }

    public boolean m10753a() {
        return this.f8085d;
    }

    public boolean m10754b() {
        return this.f8086e;
    }

    public static C3222c m10752a(String str, C3222c c3222c, C3232i c3232i) {
        C3222c c3222c2 = new C3222c();
        c3222c2.f8088g = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            c3222c2.f8087f = true;
            if (jSONObject.has("useCustomClose")) {
                c3222c2.f8086e = true;
            }
            c3222c2.f8085d = jSONObject.optBoolean("useCustomClose", false);
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8082c, "Invalid expand properties string passed.", e);
        }
        return c3222c2;
    }

    public String m10755c() {
        return this.f8088g;
    }
}
