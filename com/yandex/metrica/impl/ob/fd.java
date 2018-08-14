package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.List;

public class fd {
    private fj f12377a;
    private boolean f12378b;
    private boolean f12379c;
    private long f12380d;
    private String f12381e;
    private List<X509Certificate> f12382f;

    fd() {
        this.f12378b = true;
        this.f12379c = false;
        this.f12380d = 86400000;
        this.f12381e = "https://certificate.mobile.yandex.net/api/v1/pins";
    }

    public fd(fj fjVar) {
        this.f12378b = true;
        this.f12379c = false;
        this.f12380d = 86400000;
        this.f12381e = "https://certificate.mobile.yandex.net/api/v1/pins";
        this.f12377a = fjVar;
    }

    public fd(fj fjVar, boolean z, boolean z2) {
        this(fjVar);
        this.f12378b = z;
        this.f12379c = z2;
    }

    public void m15992a(String str, List<X509Certificate> list) {
        this.f12381e = str;
        this.f12382f = list;
    }

    long m15991a() {
        return this.f12380d;
    }

    String m15993b() {
        return this.f12381e;
    }

    List<X509Certificate> m15994c() {
        return this.f12382f;
    }

    fj m15995d() {
        return this.f12377a;
    }

    boolean m15996e() {
        return this.f12379c;
    }

    boolean m15997f() {
        return this.f12378b;
    }
}
