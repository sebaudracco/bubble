package com.yandex.metrica.impl.ob;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class el extends CertificateException {
    public el(ff ffVar) {
        super("There is not pinned certificates among chain " + m15917a(ffVar.m16003a()));
    }

    private static String m15917a(X509Certificate[] x509CertificateArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (X509Certificate issuerDN : x509CertificateArr) {
            stringBuilder.append("ISSUER=" + issuerDN.getIssuerDN().toString() + "\n");
        }
        return stringBuilder.toString();
    }
}
