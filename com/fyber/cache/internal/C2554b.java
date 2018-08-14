package com.fyber.cache.internal;

import com.fyber.cache.internal.C2553a.C2552a;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: CacheConfigurationBuilder */
public final class C2554b implements Serializable {
    private String f6401a;
    private Integer f6402b;
    private Map<C2552a, C2556d> f6403c = new HashMap();

    public final C2554b m8150a(String str) {
        this.f6401a = str;
        return this;
    }

    public final C2554b m8149a(Integer num) {
        this.f6402b = num;
        return this;
    }

    public final C2554b m8148a(C2552a c2552a, C2556d c2556d) {
        this.f6403c.put(c2552a, c2556d);
        return this;
    }

    public final C2553a m8147a() {
        C2553a c2553a = new C2553a(this.f6401a, this.f6402b);
        for (Entry entry : this.f6403c.entrySet()) {
            c2553a.m8144a((C2552a) entry.getKey(), (C2556d) entry.getValue());
        }
        return c2553a;
    }
}
