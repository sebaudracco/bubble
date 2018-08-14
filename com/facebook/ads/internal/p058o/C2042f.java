package com.facebook.ads.internal.p058o;

import com.facebook.ads.internal.p065h.C1989c;

class C2042f {
    private final C2041a f4865a;
    private final C1989c f4866b;
    private final String f4867c;
    private final String f4868d;
    private final String f4869e;

    enum C2041a {
        f4861a,
        ERROR,
        ADS
    }

    C2042f(C2041a c2041a) {
        this(c2041a, null, null, null, null);
    }

    C2042f(C2041a c2041a, C1989c c1989c, String str, String str2, String str3) {
        this.f4865a = c2041a;
        this.f4866b = c1989c;
        this.f4867c = str;
        this.f4868d = str2;
        this.f4869e = str3;
    }

    public C1989c mo3732a() {
        return this.f4866b;
    }

    C2041a m6547b() {
        return this.f4865a;
    }

    String m6548c() {
        return this.f4867c;
    }

    String m6549d() {
        return this.f4868d;
    }

    String m6550e() {
        return this.f4869e;
    }
}
