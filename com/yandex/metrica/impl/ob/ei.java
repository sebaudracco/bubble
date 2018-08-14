package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;

class ei implements er {
    private final ek f12328a;
    private final ek f12329b;
    private final ek f12330c;

    ei(ey eyVar) {
        this.f12328a = new ek(eyVar.mo7091a());
        this.f12329b = new eo(eyVar.mo7092b());
        this.f12330c = new ek(eyVar.mo7093c());
    }

    public boolean mo7088a(X509Certificate[] x509CertificateArr) {
        return this.f12328a.mo7100a(x509CertificateArr);
    }

    public boolean mo7089b(X509Certificate[] x509CertificateArr) {
        return this.f12329b.mo7100a(x509CertificateArr);
    }

    public boolean mo7090c(X509Certificate[] x509CertificateArr) {
        return this.f12330c.mo7100a(x509CertificateArr);
    }
}
