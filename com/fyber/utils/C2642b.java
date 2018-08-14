package com.fyber.utils;

import android.net.Uri;
import android.os.Build.VERSION;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.fyber.user.User;
import cz.msebera.android.httpclient.cookie.SM;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: AbstractHttpConnection */
public abstract class C2642b<T extends C2642b<T, V>, V> {
    protected URL f6575a;
    protected boolean f6576b = false;
    protected int f6577c;
    protected Map<String, List<String>> f6578d;
    protected V f6579e;
    protected boolean f6580f = true;
    protected boolean f6581g = true;
    private Map<String, String> f6582h;

    protected abstract V mo4004a(HttpURLConnection httpURLConnection) throws IOException;

    protected C2642b(String str) throws MalformedURLException {
        Uri parse = Uri.parse(str);
        if (parse.isRelative()) {
            parse = parse.buildUpon().scheme("http").build();
        }
        this.f6575a = new URL(parse.toString());
    }

    public final T m8460a(String str, String str2) {
        if (StringUtils.notNullNorEmpty(str)) {
            if (this.f6582h == null) {
                this.f6582h = new HashMap(2);
            }
            this.f6582h.put(str, str2);
        }
        return this;
    }

    public final T m8461a(Map<String, String> map) {
        if (C2664l.m8521b(map)) {
            if (this.f6582h == null) {
                this.f6582h = new HashMap();
            }
            this.f6582h.putAll(map);
        }
        return this;
    }

    public T mo4005a() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) this.f6575a.openConnection();
        if (C2664l.m8521b(this.f6582h)) {
            for (Entry entry : this.f6582h.entrySet()) {
                String str = (String) entry.getValue();
                if (StringUtils.nullOrEmpty(str)) {
                    str = "";
                }
                httpURLConnection.addRequestProperty((String) entry.getKey(), str);
            }
        }
        if (this.f6580f) {
            String cookie = CookieManager.getInstance().getCookie(this.f6575a.getHost());
            if (!(cookie == null || cookie.isEmpty())) {
                httpURLConnection.addRequestProperty(SM.COOKIE, cookie);
            }
        }
        try {
            this.f6577c = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            FyberLogger.m8451i("AbstractHttpConnection", e.getMessage());
            this.f6577c = httpURLConnection.getResponseCode();
        }
        this.f6578d = Collections.unmodifiableMap(httpURLConnection.getHeaderFields());
        this.f6579e = mo4004a(httpURLConnection);
        if (this.f6580f) {
            mo4007e();
        }
        if (this.f6581g) {
            C2642b.m8456b(httpURLConnection);
        }
        this.f6576b = true;
        return this;
    }

    protected static void m8456b(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    protected InputStream mo4006c(HttpURLConnection httpURLConnection) throws IOException {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException e) {
            return httpURLConnection.getErrorStream();
        }
    }

    public final List<String> m8463a(String str) throws IOException {
        if (this.f6576b) {
            return (List) this.f6578d.get(str);
        }
        throw new IOException("The connection has not been opened yet.");
    }

    public final int m8464b() throws IOException {
        if (this.f6576b) {
            return this.f6577c;
        }
        throw new IOException("The connection has not been opened yet.");
    }

    public final V m8466c() throws IOException {
        if (this.f6576b) {
            return this.f6579e;
        }
        throw new IOException("The connection has not been opened yet.");
    }

    public static Map<String, String> m8457d() {
        Map<String, String> hashMap = new HashMap(1);
        Object mapToString = User.mapToString();
        if (StringUtils.nullOrEmpty(mapToString)) {
            mapToString = "";
        }
        hashMap.put("X-User-Data", mapToString);
        return hashMap;
    }

    private synchronized void mo4007e() {
        m8455b(SM.SET_COOKIE);
        m8455b(SM.SET_COOKIE2);
    }

    private void m8455b(String str) {
        List<String> list = (List) this.f6578d.get(str);
        if (list != null) {
            CookieManager instance = CookieManager.getInstance();
            for (String cookie : list) {
                instance.setCookie(this.f6575a.toString(), cookie);
            }
            if (VERSION.SDK_INT >= 21) {
                instance.flush();
            } else {
                CookieSyncManager.getInstance().sync();
            }
        }
    }
}
