package com.yandex.metrica.impl.ob;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class ez {
    private fk f12365a;

    public ez(fk fkVar) {
        this.f12365a = fkVar;
    }

    public SSLContext m15976a() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, new TrustManager[]{this.f12365a}, null);
        return instance;
    }
}
