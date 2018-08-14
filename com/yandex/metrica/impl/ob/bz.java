package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import com.yandex.metrica.impl.utils.C4525g;
import java.util.List;

public class bz extends cb {
    static final dk f12001a = new dk("UUID");
    static final dk f12002b = new dk("DEVICE_ID_POSSIBLE");
    static final dk f12003c = new dk("DEVICE_ID");
    static final dk f12004d = new dk("AD_URL_GET");
    static final dk f12005e = new dk("AD_URL_REPORT");
    static final dk f12006f = new dk("CUSTOM_HOSTS");
    static final dk f12007g = new dk("SERVER_TIME_OFFSET");
    static final dk f12008h = new dk("STARTUP_REQUEST_TIME");
    static final dk f12009i = new dk("CLIDS");
    static final dk f12010j = new dk("COOKIE_BROWSERS");
    static final dk f12011k = new dk("BIND_ID_URL");
    static final dk f12012l = new dk("REFERRER");
    static final dk f12013m = new dk("DEFERRED_DEEP_LINK_WAS_CHECKED");
    static final dk f12014n = new dk("API_LEVEL");

    public bz(bq bqVar) {
        super(bqVar);
    }

    public String m15430a(String str) {
        return m15411b(f12001a.m15730b(), str);
    }

    public String m15432b(String str) {
        return m15411b(f12003c.m15730b(), str);
    }

    public String m15429a() {
        return m15411b(f12002b.m15730b(), "");
    }

    public String m15436c(String str) {
        return m15411b(f12004d.m15730b(), str);
    }

    public String m15438d(String str) {
        return m15411b(f12005e.m15730b(), str);
    }

    public List<String> m15433b() {
        Object b = m15411b(f12006f.m15730b(), null);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        return C4525g.m16274b(b);
    }

    public long m15427a(long j) {
        return m15410b(f12007g.m15728a(), j);
    }

    public long m15431b(long j) {
        return m15410b(f12008h.m15730b(), j);
    }

    public String m15442e(String str) {
        return m15411b(f12009i.m15730b(), str);
    }

    public long m15434c(long j) {
        return m15410b(f12014n.m15730b(), j);
    }

    public String m15435c() {
        return m15411b(f12012l.m15730b(), null);
    }

    public boolean m15439d() {
        return m15412b(f12013m.m15730b(), false);
    }

    public bz m15444f(String str) {
        return (bz) m15407a(f12001a.m15730b(), str);
    }

    public bz m15445g(String str) {
        return (bz) m15407a(f12003c.m15730b(), str);
    }

    public bz m15446h(String str) {
        return (bz) m15407a(f12004d.m15730b(), str);
    }

    public bz m15428a(List<String> list) {
        return (bz) m15407a(f12006f.m15730b(), C4525g.m16270a((List) list));
    }

    public bz m15447i(String str) {
        return (bz) m15407a(f12005e.m15730b(), str);
    }

    public bz m15437d(long j) {
        return (bz) m15406a(f12007g.m15730b(), j);
    }

    public bz m15441e(long j) {
        return (bz) m15406a(f12008h.m15730b(), j);
    }

    public bz m15448j(String str) {
        return (bz) m15407a(f12009i.m15730b(), str);
    }

    public bz m15443f(long j) {
        return (bz) m15406a(f12014n.m15730b(), j);
    }

    public bz m15449k(String str) {
        return (bz) m15407a(f12002b.m15730b(), str);
    }

    public String m15450l(String str) {
        return m15411b(f12010j.m15730b(), str);
    }

    public bz m15451m(String str) {
        return (bz) m15407a(f12010j.m15730b(), str);
    }

    public String m15452n(String str) {
        return m15411b(f12011k.m15730b(), str);
    }

    public bz m15453o(String str) {
        return (bz) m15407a(f12011k.m15730b(), str);
    }

    public bz m15454p(String str) {
        return (bz) m15407a(f12012l.m15730b(), str);
    }

    public bz m15440e() {
        return (bz) m15408a(f12013m.m15730b(), true);
    }
}
