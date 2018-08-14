package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;

class ep implements er {
    private final ek f12339a;
    private final ek f12340b;

    ep(ey eyVar) {
        this.f12339a = new ek(eyVar.mo7091a());
        this.f12340b = new ek(eyVar.mo7093c());
    }

    public boolean mo7088a(X509Certificate[] x509CertificateArr) {
        return this.f12339a.mo7100a(x509CertificateArr);
    }

    public boolean mo7089b(X509Certificate[] x509CertificateArr) {
        return false;
    }

    public boolean mo7090c(X509Certificate[] x509CertificateArr) {
        return this.f12340b.mo7100a(x509CertificateArr);
    }
}
