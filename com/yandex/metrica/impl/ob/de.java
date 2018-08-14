package com.yandex.metrica.impl.ob;

import android.content.Context;
import java.util.Map;

public class de extends dd {
    private static final dk f12142c = new dk("UUID");
    private static final dk f12143d = new dk("DEVICEID");
    private static final dk f12144e = new dk("DEVICEID_2");
    private static final dk f12145f = new dk("DEVICEID_3");
    private static final dk f12146g = new dk("AD_URL_GET");
    private static final dk f12147h = new dk("AD_URL_REPORT");
    private static final dk f12148i = new dk("HOST_URL");
    private static final dk f12149j = new dk("SERVER_TIME_OFFSET");
    private static final dk f12150k = new dk("STARTUP_REQUEST_TIME");
    private static final dk f12151l = new dk("CLIDS");
    private dk f12152m;
    private dk f12153n;
    private dk f12154o;
    private dk f12155p;
    private dk f12156q;
    private dk f12157r;
    private dk f12158s;
    private dk f12159t;
    private dk f12160u;

    static {
        dk dkVar = new dk("UUID_SOURCE");
    }

    public de(Context context) {
        super(context, null);
    }

    protected void mo7073h() {
        super.mo7073h();
        this.f12152m = new dk(f12142c.m15728a());
        this.f12153n = new dk(f12143d.m15728a());
        this.f12154o = new dk(f12144e.m15728a());
        this.f12155p = new dk(f12145f.m15728a());
        this.f12156q = new dk(f12146g.m15728a());
        this.f12157r = new dk(f12147h.m15728a());
        dk dkVar = new dk(f12148i.m15728a());
        this.f12158s = new dk(f12149j.m15728a());
        this.f12159t = new dk(f12150k.m15728a());
        this.f12160u = new dk(f12151l.m15728a());
    }

    protected String mo7072f() {
        return "_startupinfopreferences";
    }

    public String m15664a(String str) {
        return this.b.getString(this.f12152m.m15730b(), str);
    }

    public String m15667b(String str) {
        return this.b.getString(this.f12155p.m15730b(), str);
    }

    public String m15663a() {
        return this.b.getString(this.f12154o.m15730b(), this.b.getString(this.f12153n.m15730b(), ""));
    }

    public String m15668c(String str) {
        return this.b.getString(this.f12156q.m15730b(), str);
    }

    public String m15670d(String str) {
        return this.b.getString(this.f12157r.m15730b(), str);
    }

    public long m15662a(long j) {
        return this.b.getLong(this.f12158s.m15728a(), j);
    }

    public long m15665b(long j) {
        return this.b.getLong(this.f12159t.m15730b(), j);
    }

    public String m15671e(String str) {
        return this.b.getString(this.f12160u.m15730b(), str);
    }

    public de m15666b() {
        return (de) m15644i();
    }

    public Map<String, ?> m15669c() {
        return this.b.getAll();
    }
}
