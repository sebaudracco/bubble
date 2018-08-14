package com.yandex.metrica.impl.ob;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public abstract class fu<T> {
    private final int f12401a;
    private final String f12402b;
    private fw f12403c;
    private volatile C4471b<T> f12404d;
    private volatile C4473a f12405e;

    public interface C4471b<T> {
        void mo7101a(T t);
    }

    public interface C4473a {
        void mo7102a(fr frVar);
    }

    protected abstract T mo7103b(ft ftVar) throws fr;

    public fu(int i, String str) {
        this.f12401a = i;
        this.f12402b = str;
        m16032a(new fw());
    }

    public int m16039d() {
        return this.f12401a;
    }

    public fu<?> m16032a(fw fwVar) {
        this.f12403c = fwVar;
        return this;
    }

    public String mo7105a() {
        return this.f12402b;
    }

    protected C4471b<T> m16040e() {
        return this.f12404d;
    }

    protected void m16035a(C4471b<T> c4471b) {
        this.f12404d = c4471b;
    }

    protected C4473a m16041f() {
        return this.f12405e;
    }

    protected void m16034a(C4473a c4473a) {
        this.f12405e = c4473a;
    }

    public Map<String, String> mo7106b() throws fr {
        return Collections.emptyMap();
    }

    protected Map<String, String> m16042g() throws fr {
        return m16046k();
    }

    protected String m16043h() {
        return m16047l();
    }

    public String m16044i() {
        return m16048m();
    }

    public byte[] m16045j() throws fr {
        Map g = m16042g();
        if (g == null || g.size() <= 0) {
            return null;
        }
        return m16031a(g, m16043h());
    }

    protected Map<String, String> m16046k() throws fr {
        return null;
    }

    protected String m16047l() {
        return "UTF-8";
    }

    public String m16048m() {
        return "application/x-www-form-urlencoded; charset=" + m16047l();
    }

    public byte[] mo7104c() throws fr {
        Map k = m16046k();
        if (k == null || k.size() <= 0) {
            return null;
        }
        return m16031a(k, m16047l());
    }

    private static byte[] m16031a(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (Throwable e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    public final int m16049n() {
        return this.f12403c.m16078a();
    }

    public fw m16050o() {
        return this.f12403c;
    }
}
