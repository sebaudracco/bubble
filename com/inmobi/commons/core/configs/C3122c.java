package com.inmobi.commons.core.configs;

import com.inmobi.commons.core.p116c.C3116c;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ConfigDao */
public class C3122c {
    private C3116c f7657a = C3116c.m10143b("config_store");

    public static String m10194a() {
        return C3116c.m10142a("config_store");
    }

    public void m10195a(C3045a c3045a) {
        try {
            this.f7657a.m10146a(c3045a.mo6231a() + "_config", c3045a.mo6233b().toString());
            m10196a(c3045a.mo6231a(), System.currentTimeMillis());
        } catch (JSONException e) {
        }
    }

    public void m10199b(C3045a c3045a) {
        String b = this.f7657a.m10150b(c3045a.mo6231a() + "_config", null);
        if (b != null) {
            try {
                c3045a.mo6232a(new JSONObject(b));
            } catch (JSONException e) {
            }
        }
    }

    public boolean m10197a(String str) {
        return this.f7657a.m10150b(new StringBuilder().append(str).append("_config").toString(), null) != null;
    }

    public long m10198b(String str) {
        return this.f7657a.m10149b(str + "_config_update_ts", 0);
    }

    public void m10196a(String str, long j) {
        this.f7657a.m10145a(str + "_config_update_ts", j);
    }
}
