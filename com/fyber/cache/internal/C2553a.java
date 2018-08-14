package com.fyber.cache.internal;

import cz.msebera.android.httpclient.HttpStatus;
import java.io.Serializable;

/* compiled from: CacheConfiguration */
public final class C2553a implements Serializable {
    public static final C2553a f6397a = new C2553a("", Integer.valueOf(-1));
    private String f6398b;
    private Integer f6399c;
    private C2556d[] f6400d = new C2556d[C2552a.values().length];

    /* compiled from: CacheConfiguration */
    public enum C2552a {
        f6394a,
        CELLULAR
    }

    public C2553a(String str, Integer num) {
        this.f6398b = str;
        if (num == null) {
            num = Integer.valueOf(3600);
        } else if (num.intValue() < HttpStatus.SC_MULTIPLE_CHOICES) {
            num = Integer.valueOf(HttpStatus.SC_MULTIPLE_CHOICES);
        }
        this.f6399c = num;
    }

    public final String m8143a() {
        return this.f6398b;
    }

    public final int m8145b() {
        return this.f6399c.intValue();
    }

    public final C2556d m8142a(C2552a c2552a) {
        return this.f6400d[c2552a.ordinal()];
    }

    public final void m8144a(C2552a c2552a, C2556d c2556d) {
        this.f6400d[c2552a.ordinal()] = c2556d;
    }

    public final int m8146c() {
        return Math.max(this.f6400d[0].m8161a(), this.f6400d[1].m8161a());
    }
}
