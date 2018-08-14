package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.Set;

public class eo extends ek {
    private fb f12338a;

    public eo(fb fbVar) {
        super(fbVar);
        this.f12338a = fbVar;
    }

    public boolean mo7100a(X509Certificate[] x509CertificateArr) {
        Set b = this.f12338a.m15986b();
        if (b.isEmpty()) {
            return false;
        }
        return b.contains(fg.m16007a(x509CertificateArr[0]));
    }
}
