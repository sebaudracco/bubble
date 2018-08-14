package com.fyber.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/* compiled from: HttpConnection */
public final class C2657h extends C2642b<C2657h, String> {
    public static C2657h m8506b(String str) throws MalformedURLException {
        return new C2657h(str);
    }

    private C2657h(String str) throws MalformedURLException {
        super(str);
    }

    protected final /* synthetic */ Object mo4004a(HttpURLConnection httpURLConnection) throws IOException {
        InputStream c = mo4006c(httpURLConnection);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = c.read(bArr);
            if (read > 0) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            }
        }
    }
}
