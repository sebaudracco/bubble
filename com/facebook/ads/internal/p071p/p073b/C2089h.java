package com.facebook.ads.internal.p071p.p073b;

import android.text.TextUtils;
import android.util.Log;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class C2089h implements C2088n {
    public final String f4957a;
    private HttpURLConnection f4958b;
    private InputStream f4959c;
    private volatile int f4960d;
    private volatile String f4961e;

    public C2089h(C2089h c2089h) {
        this.f4960d = Integer.MIN_VALUE;
        this.f4957a = c2089h.f4957a;
        this.f4961e = c2089h.f4961e;
        this.f4960d = c2089h.f4960d;
    }

    public C2089h(String str) {
        this(str, C2095m.m6736a(str));
    }

    public C2089h(String str, String str2) {
        this.f4960d = Integer.MIN_VALUE;
        this.f4957a = (String) C2092j.m6733a(str);
        this.f4961e = str2;
    }

    private int m6725a(HttpURLConnection httpURLConnection, int i, int i2) {
        int contentLength = httpURLConnection.getContentLength();
        return i2 == 200 ? contentLength : i2 == HttpStatus.SC_PARTIAL_CONTENT ? contentLength + i : this.f4960d;
    }

    private HttpURLConnection m6726a(int i, int i2) {
        HttpURLConnection httpURLConnection;
        String str = this.f4957a;
        int i3 = 0;
        Object obj;
        do {
            Log.d("ProxyCache", "Open connection " + (i > 0 ? " with offset " + i : "") + " to " + str);
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            if (i > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + i + "-");
            }
            if (i2 > 0) {
                httpURLConnection.setConnectTimeout(i2);
                httpURLConnection.setReadTimeout(i2);
            }
            int responseCode = httpURLConnection.getResponseCode();
            obj = (responseCode == HttpStatus.SC_MOVED_PERMANENTLY || responseCode == HttpStatus.SC_MOVED_TEMPORARILY || responseCode == HttpStatus.SC_SEE_OTHER) ? 1 : null;
            if (obj != null) {
                str = httpURLConnection.getHeaderField(HttpHeaders.LOCATION);
                i3++;
                httpURLConnection.disconnect();
            }
            if (i3 > 5) {
                throw new C2090l("Too many redirects: " + i3);
            }
        } while (obj != null);
        return httpURLConnection;
    }

    private void m6727d() {
        HttpURLConnection a;
        Throwable e;
        Closeable closeable = null;
        Log.d("ProxyCache", "Read content info from " + this.f4957a);
        try {
            a = m6726a(0, 10000);
            try {
                this.f4960d = a.getContentLength();
                this.f4961e = a.getContentType();
                closeable = a.getInputStream();
                Log.i("ProxyCache", "Content info for `" + this.f4957a + "`: mime: " + this.f4961e + ", content-length: " + this.f4960d);
                C2095m.m6738a(closeable);
                if (a != null) {
                    a.disconnect();
                }
            } catch (IOException e2) {
                e = e2;
                try {
                    Log.e("ProxyCache", "Error fetching info from " + this.f4957a, e);
                    C2095m.m6738a(closeable);
                    if (a != null) {
                        a.disconnect();
                    }
                } catch (Throwable th) {
                    e = th;
                    C2095m.m6738a(closeable);
                    if (a != null) {
                        a.disconnect();
                    }
                    throw e;
                }
            }
        } catch (IOException e3) {
            e = e3;
            a = null;
            Log.e("ProxyCache", "Error fetching info from " + this.f4957a, e);
            C2095m.m6738a(closeable);
            if (a != null) {
                a.disconnect();
            }
        } catch (Throwable th2) {
            e = th2;
            a = null;
            C2095m.m6738a(closeable);
            if (a != null) {
                a.disconnect();
            }
            throw e;
        }
    }

    public synchronized int mo3757a() {
        if (this.f4960d == Integer.MIN_VALUE) {
            m6727d();
        }
        return this.f4960d;
    }

    public int mo3758a(byte[] bArr) {
        if (this.f4959c == null) {
            throw new C2090l("Error reading data from " + this.f4957a + ": connection is absent!");
        }
        try {
            return this.f4959c.read(bArr, 0, bArr.length);
        } catch (Throwable e) {
            throw new C2091i("Reading source " + this.f4957a + " is interrupted", e);
        } catch (Throwable e2) {
            throw new C2090l("Error reading data from " + this.f4957a, e2);
        }
    }

    public void mo3759a(int i) {
        try {
            this.f4958b = m6726a(i, -1);
            this.f4961e = this.f4958b.getContentType();
            this.f4959c = new BufferedInputStream(this.f4958b.getInputStream(), 8192);
            this.f4960d = m6725a(this.f4958b, i, this.f4958b.getResponseCode());
        } catch (Throwable e) {
            throw new C2090l("Error opening connection for " + this.f4957a + " with offset " + i, e);
        }
    }

    public void mo3760b() {
        if (this.f4958b != null) {
            try {
                this.f4958b.disconnect();
            } catch (Throwable e) {
                throw new C2090l("Error disconnecting HttpUrlConnection", e);
            }
        }
    }

    public synchronized String m6732c() {
        if (TextUtils.isEmpty(this.f4961e)) {
            m6727d();
        }
        return this.f4961e;
    }

    public String toString() {
        return "HttpUrlSource{url='" + this.f4957a + "}";
    }
}
