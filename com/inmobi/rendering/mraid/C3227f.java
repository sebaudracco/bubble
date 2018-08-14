package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.p116c.C3116c;

/* compiled from: MraidJsDao */
public class C3227f {
    private C3116c f8110a = C3116c.m10143b("mraid_js_store");

    public static String m10773a() {
        return C3116c.m10142a("mraid_js_store");
    }

    public void m10774a(String str) {
        this.f8110a.m10146a("mraid_js_string", str);
        this.f8110a.m10145a("last_updated_ts", System.currentTimeMillis() / 1000);
    }

    public String m10775b() {
        return this.f8110a.m10150b("mraid_js_string", null);
    }

    public long m10776c() {
        return this.f8110a.m10149b("last_updated_ts", 0);
    }
}
