package com.facebook.ads.internal.p071p.p072a;

import android.os.Build.VERSION;
import android.util.Log;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.net.ssl.HttpsURLConnection;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C2048a {
    private static int[] f4874f = new int[20];
    private static final String f4875g = C2048a.class.getSimpleName();
    protected final C2045q f4876a = new C20471(this);
    protected final C2050d f4877b = new C2051e();
    protected C2052r f4878c = new C2053g();
    protected int f4879d = 2000;
    protected int f4880e = 8000;
    private int f4881h = 3;
    private Map<String, String> f4882i = new TreeMap();
    private boolean f4883j;
    private Set<String> f4884k;
    private Set<String> f4885l;

    class C20471 extends C2046f {
        final /* synthetic */ C2048a f4873a;

        C20471(C2048a c2048a) {
            this.f4873a = c2048a;
        }
    }

    static {
        C2048a.m6570c();
        if (VERSION.SDK_INT > 8) {
            C2048a.m6569a();
        }
    }

    public static void m6569a() {
        if (CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }

    private static void m6570c() {
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", SchemaSymbols.ATTVAL_FALSE);
        }
    }

    private void m6571c(HttpURLConnection httpURLConnection) {
        for (String str : this.f4882i.keySet()) {
            httpURLConnection.setRequestProperty(str, (String) this.f4882i.get(str));
        }
    }

    protected int m6572a(int i) {
        return f4874f[i + 2] * 1000;
    }

    protected int m6573a(HttpURLConnection httpURLConnection, byte[] bArr) {
        OutputStream outputStream = null;
        try {
            outputStream = this.f4876a.mo3733a(httpURLConnection);
            if (outputStream != null) {
                this.f4876a.mo3735a(outputStream, bArr);
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                }
            }
            return responseCode;
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e2) {
                }
            }
        }
    }

    public C2048a m6574a(String str, String str2) {
        this.f4882i.put(str, str2);
        return this;
    }

    public C2060n m6575a(C2055l c2055l) {
        int i = 0;
        long currentTimeMillis = System.currentTimeMillis();
        while (i < this.f4881h) {
            try {
                m6592c(m6572a(i));
                if (this.f4878c.mo3744a()) {
                    this.f4878c.mo3742a((i + 1) + "of" + this.f4881h + ", trying " + c2055l.m6608a());
                }
                currentTimeMillis = System.currentTimeMillis();
                C2060n a = m6576a(c2055l.m6608a(), c2055l.m6609b(), c2055l.m6610c(), c2055l.m6611d());
                if (a != null) {
                    return a;
                }
                i++;
            } catch (C2059m e) {
                if (m6585a((Throwable) e, currentTimeMillis) && i < this.f4881h - 1) {
                    continue;
                } else if (!this.f4876a.mo3737a(e) || i >= this.f4881h - 1) {
                    throw e;
                } else {
                    try {
                        Thread.sleep((long) this.f4879d);
                    } catch (InterruptedException e2) {
                        throw e;
                    }
                }
            }
        }
        return null;
    }

    protected C2060n m6576a(String str, C2057j c2057j, String str2, byte[] bArr) {
        HttpURLConnection a;
        Throwable e;
        C2060n a2;
        HttpURLConnection httpURLConnection;
        Exception exception;
        C2060n c2060n = null;
        Object obj = 1;
        C2060n c2060n2 = null;
        try {
            this.f4883j = false;
            a = m6580a(str);
            try {
                m6583a(a, c2057j, str2);
                m6571c(a);
                if (this.f4878c.mo3744a()) {
                    this.f4878c.mo3743a(a, bArr);
                }
                a.connect();
                this.f4883j = true;
                Object obj2 = (this.f4885l == null || this.f4885l.isEmpty()) ? null : 1;
                if (this.f4884k == null || this.f4884k.isEmpty()) {
                    obj = null;
                }
                if ((a instanceof HttpsURLConnection) && !(obj2 == null && r1 == null)) {
                    try {
                        C2061o.m6622a((HttpsURLConnection) a, this.f4885l, this.f4884k);
                    } catch (Throwable e2) {
                        Log.e(f4875g, "Unable to validate SSL certificates.", e2);
                    } catch (Throwable th) {
                        e2 = th;
                        if (this.f4878c.mo3744a()) {
                            this.f4878c.mo3741a(c2060n);
                        }
                        if (a != null) {
                            a.disconnect();
                        }
                        throw e2;
                    }
                }
                if (a.getDoOutput() && bArr != null) {
                    m6573a(a, bArr);
                }
                a2 = a.getDoInput() ? m6579a(a) : new C2060n(a, null);
                if (this.f4878c.mo3744a()) {
                    this.f4878c.mo3741a(a2);
                }
                if (a == null) {
                    return a2;
                }
                a.disconnect();
                return a2;
            } catch (Exception e3) {
                httpURLConnection = a;
                exception = e3;
                try {
                    a2 = m6588b(httpURLConnection);
                    if (a2 != null) {
                        try {
                            if (a2.m6616a() > 0) {
                                if (this.f4878c.mo3744a()) {
                                    this.f4878c.mo3741a(a2);
                                }
                                if (httpURLConnection != null) {
                                    return a2;
                                }
                                httpURLConnection.disconnect();
                                return a2;
                            }
                        } catch (Throwable th2) {
                            c2060n = a2;
                            e2 = th2;
                            a = httpURLConnection;
                            if (this.f4878c.mo3744a()) {
                                this.f4878c.mo3741a(c2060n);
                            }
                            if (a != null) {
                                a.disconnect();
                            }
                            throw e2;
                        }
                    }
                    throw new C2059m(exception, a2);
                } catch (Exception e4) {
                    exception.printStackTrace();
                    if (null != null) {
                        if (c2060n2.m6616a() > 0) {
                            if (this.f4878c.mo3744a()) {
                                this.f4878c.mo3741a(null);
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return null;
                        }
                    }
                    throw new C2059m(exception, c2060n2);
                } catch (Throwable th3) {
                    e2 = th3;
                    a = httpURLConnection;
                    if (this.f4878c.mo3744a()) {
                        this.f4878c.mo3741a(c2060n);
                    }
                    if (a != null) {
                        a.disconnect();
                    }
                    throw e2;
                }
            } catch (Throwable th4) {
                e2 = th4;
                if (this.f4878c.mo3744a()) {
                    this.f4878c.mo3741a(c2060n);
                }
                if (a != null) {
                    a.disconnect();
                }
                throw e2;
            }
        } catch (Exception e32) {
            exception = e32;
            httpURLConnection = null;
            a2 = m6588b(httpURLConnection);
            if (a2 != null) {
                if (a2.m6616a() > 0) {
                    if (this.f4878c.mo3744a()) {
                        this.f4878c.mo3741a(a2);
                    }
                    if (httpURLConnection != null) {
                        return a2;
                    }
                    httpURLConnection.disconnect();
                    return a2;
                }
            }
            throw new C2059m(exception, a2);
        } catch (Throwable th5) {
            e2 = th5;
            a = null;
            if (this.f4878c.mo3744a()) {
                this.f4878c.mo3741a(c2060n);
            }
            if (a != null) {
                a.disconnect();
            }
            throw e2;
        }
    }

    public C2060n m6577a(String str, C2062p c2062p) {
        return m6586b(new C2056i(str, c2062p));
    }

    public C2060n m6578a(String str, String str2, byte[] bArr) {
        return m6586b(new C2058k(str, null, str2, bArr));
    }

    protected C2060n m6579a(HttpURLConnection httpURLConnection) {
        InputStream b;
        Throwable th;
        byte[] bArr = null;
        try {
            b = this.f4876a.mo3739b(httpURLConnection);
            if (b != null) {
                try {
                    bArr = this.f4876a.mo3738a(b);
                } catch (Throwable th2) {
                    th = th2;
                    if (b != null) {
                        try {
                            b.close();
                        } catch (Exception e) {
                        }
                    }
                    throw th;
                }
            }
            C2060n c2060n = new C2060n(httpURLConnection, bArr);
            if (b != null) {
                try {
                    b.close();
                } catch (Exception e2) {
                }
            }
            return c2060n;
        } catch (Throwable th3) {
            th = th3;
            b = null;
            if (b != null) {
                b.close();
            }
            throw th;
        }
    }

    protected HttpURLConnection m6580a(String str) {
        try {
            URL url = new URL(str);
            return this.f4876a.mo3734a(str);
        } catch (Throwable e) {
            throw new IllegalArgumentException(str + " is not a valid URL", e);
        }
    }

    protected void m6581a(C2055l c2055l, C2035b c2035b) {
        this.f4877b.mo3740a(this, c2035b).mo3745a(c2055l);
    }

    public void m6582a(String str, C2062p c2062p, C2035b c2035b) {
        m6581a(new C2058k(str, c2062p), c2035b);
    }

    protected void m6583a(HttpURLConnection httpURLConnection, C2057j c2057j, String str) {
        httpURLConnection.setConnectTimeout(this.f4879d);
        httpURLConnection.setReadTimeout(this.f4880e);
        this.f4876a.mo3736a(httpURLConnection, c2057j, str);
    }

    public void m6584a(Set<String> set) {
        this.f4885l = set;
    }

    protected boolean m6585a(Throwable th, long j) {
        long currentTimeMillis = (System.currentTimeMillis() - j) + 10;
        if (this.f4878c.mo3744a()) {
            this.f4878c.mo3742a("ELAPSED TIME = " + currentTimeMillis + ", CT = " + this.f4879d + ", RT = " + this.f4880e);
        }
        return this.f4883j ? currentTimeMillis >= ((long) this.f4880e) : currentTimeMillis >= ((long) this.f4879d);
    }

    public C2060n m6586b(C2055l c2055l) {
        C2060n c2060n = null;
        try {
            c2060n = m6576a(c2055l.m6608a(), c2055l.m6609b(), c2055l.m6610c(), c2055l.m6611d());
        } catch (C2059m e) {
            this.f4876a.mo3737a(e);
        } catch (Exception e2) {
            this.f4876a.mo3737a(new C2059m(e2, c2060n));
        }
        return c2060n;
    }

    public C2060n m6587b(String str, C2062p c2062p) {
        return m6586b(new C2058k(str, c2062p));
    }

    protected C2060n m6588b(HttpURLConnection httpURLConnection) {
        Throwable th;
        byte[] bArr = null;
        InputStream errorStream;
        try {
            errorStream = httpURLConnection.getErrorStream();
            if (errorStream != null) {
                try {
                    bArr = this.f4876a.mo3738a(errorStream);
                } catch (Throwable th2) {
                    th = th2;
                    if (errorStream != null) {
                        try {
                            errorStream.close();
                        } catch (Exception e) {
                        }
                    }
                    throw th;
                }
            }
            C2060n c2060n = new C2060n(httpURLConnection, bArr);
            if (errorStream != null) {
                try {
                    errorStream.close();
                } catch (Exception e2) {
                }
            }
            return c2060n;
        } catch (Throwable th3) {
            th = th3;
            errorStream = null;
            if (errorStream != null) {
                errorStream.close();
            }
            throw th;
        }
    }

    public C2062p m6589b() {
        return new C2062p();
    }

    public void m6590b(int i) {
        if (i < 1 || i > 18) {
            throw new IllegalArgumentException("Maximum retries must be between 1 and 18");
        }
        this.f4881h = i;
    }

    public void m6591b(Set<String> set) {
        this.f4884k = set;
    }

    public void m6592c(int i) {
        this.f4879d = i;
    }
}
