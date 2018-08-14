package com.facebook.ads.internal.p071p.p072a;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class C2060n {
    private int f4900a;
    private String f4901b;
    private Map<String, List<String>> f4902c;
    private byte[] f4903d;

    public C2060n(HttpURLConnection httpURLConnection, byte[] bArr) {
        try {
            this.f4900a = httpURLConnection.getResponseCode();
            this.f4901b = httpURLConnection.getURL().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.f4902c = httpURLConnection.getHeaderFields();
        this.f4903d = bArr;
    }

    public int m6616a() {
        return this.f4900a;
    }

    public String m6617b() {
        return this.f4901b;
    }

    public Map<String, List<String>> m6618c() {
        return this.f4902c;
    }

    public byte[] m6619d() {
        return this.f4903d;
    }

    public String m6620e() {
        return this.f4903d != null ? new String(this.f4903d) : null;
    }
}
