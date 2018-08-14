package com.inmobi.commons.core.utilities.info;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: SessionInfo */
public class C3164f {
    private static final String f7821a = C3164f.class.getSimpleName();
    private static C3164f f7822b;
    private static Object f7823c = new Object();
    private String f7824d;
    private long f7825e;
    private long f7826f;
    private boolean f7827g;

    public static C3164f m10487a() {
        C3164f c3164f = f7822b;
        if (c3164f == null) {
            synchronized (f7823c) {
                c3164f = f7822b;
                if (c3164f == null) {
                    f7822b = new C3164f();
                    c3164f = f7822b;
                }
            }
        }
        return c3164f;
    }

    private C3164f() {
    }

    public void m10490a(String str) {
        this.f7824d = str;
    }

    public void m10489a(long j) {
        this.f7825e = j;
    }

    public void m10493b(long j) {
        this.f7826f = j;
    }

    public JSONObject m10492b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sid", this.f7824d);
            jSONObject.put("s-ts", this.f7825e);
            jSONObject.put("e-ts", this.f7826f);
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f7821a, "Problem converting session object to Json.", e);
        }
        return jSONObject;
    }

    public void m10491a(boolean z) {
        this.f7827g = z;
        if (!this.f7827g) {
            m10488e();
        }
    }

    private void m10488e() {
        this.f7824d = null;
        this.f7825e = 0;
        this.f7826f = 0;
    }

    public String m10494c() {
        return this.f7824d;
    }

    public HashMap<String, String> m10495d() {
        HashMap<String, String> hashMap = new HashMap();
        if (this.f7827g && this.f7824d != null) {
            hashMap.put("u-s-id", this.f7824d);
        }
        return hashMap;
    }
}
