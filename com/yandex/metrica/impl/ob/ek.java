package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.Set;

class ek {
    private fb f12332a;

    public ek(fb fbVar) {
        this.f12332a = fbVar;
    }

    public boolean mo7100a(X509Certificate[] x509CertificateArr) {
        Set b = this.f12332a.m15986b();
        if (b.isEmpty()) {
            return false;
        }
        for (X509Certificate a : x509CertificateArr) {
            if (b.contains(fg.m16007a(a))) {
                return true;
            }
        }
        return false;
    }
}
