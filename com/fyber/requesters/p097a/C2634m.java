package com.fyber.requesters.p097a;

import com.fyber.utils.C2672t;
import com.fyber.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RequestData */
public final class C2634m {
    protected String f6544a;
    protected Map<String, String> f6545b = new HashMap(3);
    protected C2672t f6546c;
    private boolean f6547d;

    public C2634m(C2672t c2672t) {
        this.f6546c = c2672t;
    }

    public final C2634m m8433a(String str, String str2) {
        if (StringUtils.notNullNorEmpty(str)) {
            this.f6545b.put(str, str2);
        }
        return this;
    }

    public final String m8435a() {
        if (StringUtils.nullOrEmpty(this.f6544a)) {
            m8436b();
        }
        return this.f6544a;
    }

    public final C2634m m8436b() {
        this.f6544a = this.f6546c.m8547e();
        return this;
    }

    public final C2672t m8437c() {
        return this.f6546c;
    }

    public final Map<String, String> m8438d() {
        return this.f6545b;
    }

    public final C2634m m8434a(boolean z) {
        this.f6547d = z;
        return this;
    }

    public final boolean m8439e() {
        return this.f6547d;
    }
}
