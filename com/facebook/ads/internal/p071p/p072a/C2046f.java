package com.facebook.ads.internal.p071p.p072a;

import cz.msebera.android.httpclient.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class C2046f implements C2045q {
    private final C2052r f4872a;

    public C2046f() {
        this(new C2053g());
    }

    public C2046f(C2052r c2052r) {
        this.f4872a = c2052r;
    }

    public OutputStream mo3733a(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getOutputStream();
    }

    public HttpURLConnection mo3734a(String str) {
        return (HttpURLConnection) new URL(str).openConnection();
    }

    public void mo3735a(OutputStream outputStream, byte[] bArr) {
        outputStream.write(bArr);
    }

    public void mo3736a(HttpURLConnection httpURLConnection, C2057j c2057j, String str) {
        httpURLConnection.setRequestMethod(c2057j.m6614c());
        httpURLConnection.setDoOutput(c2057j.m6613b());
        httpURLConnection.setDoInput(c2057j.m6612a());
        if (str != null) {
            httpURLConnection.setRequestProperty("Content-Type", str);
        }
        httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
    }

    public boolean mo3737a(C2059m c2059m) {
        C2060n a = c2059m.m6615a();
        if (this.f4872a.mo3744a()) {
            this.f4872a.mo3742a("BasicRequestHandler.onError got");
            c2059m.printStackTrace();
        }
        return a != null && a.m6616a() > 0;
    }

    public byte[] mo3738a(InputStream inputStream) {
        byte[] bArr = new byte[16384];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public InputStream mo3739b(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getInputStream();
    }
}
