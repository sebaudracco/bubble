package com.inmobi.signals.p120b;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONObject;

/* compiled from: WifiInfo */
public class C3254a {
    private static final String f8183a = C3254a.class.getSimpleName();
    private long f8184b;
    private String f8185c;
    private int f8186d;
    private int f8187e;

    public void m10884a(long j) {
        this.f8184b = j;
    }

    public void m10885a(String str) {
        this.f8185c = str;
    }

    public void m10883a(int i) {
        this.f8186d = i;
    }

    public void m10887b(int i) {
        this.f8187e = i;
    }

    public long m10882a() {
        return this.f8184b;
    }

    public JSONObject m10886b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bssid", this.f8184b);
            jSONObject.put("essid", this.f8185c);
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8183a, "Error while converting WifiInfo to string.", e);
        }
        return jSONObject;
    }
}
