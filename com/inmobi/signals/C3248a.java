package com.inmobi.signals;

import com.inmobi.commons.core.p116c.C3116c;

/* compiled from: CarbDao */
public class C3248a {
    private C3116c f8170a = C3116c.m10143b("carb_store");

    public static String m10859a() {
        return C3116c.m10142a("carb_store");
    }

    public long m10861b() {
        return this.f8170a.m10149b("carb_last_update_ts", 0);
    }

    public void m10860a(long j) {
        this.f8170a.m10145a("carb_last_update_ts", j);
    }
}
