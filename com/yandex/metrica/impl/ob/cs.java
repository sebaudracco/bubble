package com.yandex.metrica.impl.ob;

import java.io.IOException;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

public class cs extends cr {
    cs(String str) {
        super(str);
    }

    public HttpURLConnection mo7070a() throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.mo7070a();
        httpsURLConnection.setSSLSocketFactory(co.m15605a().m15609b());
        return httpsURLConnection;
    }

    public boolean mo7069b() {
        return co.m15605a().m15610c();
    }
}
