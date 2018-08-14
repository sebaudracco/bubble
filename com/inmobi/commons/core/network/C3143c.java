package com.inmobi.commons.core.network;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.List;
import java.util.Map;

/* compiled from: NetworkResponse */
public class C3143c {
    private static final String f7752a = C3143c.class.getSimpleName();
    private NetworkRequest f7753b;
    private String f7754c;
    private byte[] f7755d;
    private NetworkError f7756e;
    private Map<String, List<String>> f7757f;

    public C3143c(NetworkRequest networkRequest) {
        this.f7753b = networkRequest;
    }

    public boolean m10351a() {
        return this.f7756e != null;
    }

    public String m10352b() {
        return this.f7754c;
    }

    public void m10348a(String str) {
        this.f7754c = str;
    }

    public byte[] m10353c() {
        if (this.f7755d == null || this.f7755d.length == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[this.f7755d.length];
        System.arraycopy(this.f7755d, 0, bArr, 0, this.f7755d.length);
        return bArr;
    }

    public void m10350a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            this.f7755d = new byte[0];
            return;
        }
        this.f7755d = new byte[bArr.length];
        System.arraycopy(bArr, 0, this.f7755d, 0, bArr.length);
    }

    public Map<String, List<String>> m10354d() {
        return this.f7757f;
    }

    public void m10349a(Map<String, List<String>> map) {
        this.f7757f = map;
    }

    public NetworkError m10355e() {
        return this.f7756e;
    }

    public void m10347a(NetworkError networkError) {
        this.f7756e = networkError;
    }

    public long m10356f() {
        try {
            return 0 + ((long) this.f7754c.length());
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7752a, "SDK encountered unexpected error in computing response size; " + e.getMessage());
            return 0;
        }
    }
}
