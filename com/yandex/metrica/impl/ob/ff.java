package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.concurrent.atomic.AtomicInteger;

public class ff {
    private static final AtomicInteger f12386a = new AtomicInteger(0);
    private X509Certificate[] f12387b;
    private boolean f12388c = false;

    ff(X509Certificate[] x509CertificateArr) {
        f12386a.incrementAndGet();
        this.f12387b = x509CertificateArr;
    }

    public X509Certificate[] m16003a() {
        return (X509Certificate[]) this.f12387b.clone();
    }

    public boolean m16004b() {
        return this.f12388c;
    }

    void m16005c() {
        this.f12388c = true;
    }
}
