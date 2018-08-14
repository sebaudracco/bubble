package com.yandex.metrica.impl.ob;

import com.yandex.metrica.CounterConfiguration.C4270a;
import com.yandex.metrica.impl.C4305a.C4304a;

public class ca extends cb {
    private static final dk f12016a = new dk("SESSION_SLEEP_START");
    private static final dk f12017b = new dk("SESSION_ID");
    private static final dk f12018c = new dk("SESSION_COUNTER_ID");
    private static final dk f12019d = new dk("SESSION_INIT_TIME");
    private static final dk f12020e = new dk("SESSION_IS_ALIVE_REPORT_NEEDED");
    private static final dk f12021f = new dk("BG_SESSION_ID");
    private static final dk f12022g = new dk("BG_SESSION_SLEEP_START");
    private static final dk f12023h = new dk("BG_SESSION_COUNTER_ID");
    private static final dk f12024i = new dk("BG_SESSION_INIT_TIME");
    private static final dk f12025j = new dk("BG_SESSION_IS_ALIVE_REPORT_NEEDED");
    private static final dk f12026k = new dk("COLLECT_INSTALLED_APPS");
    private static final dk f12027l = new dk("IDENTITY_SEND_TIME");
    private static final dk f12028m = new dk("PERMISSIONS_CHECK_TIME");
    private static final dk f12029n = new dk("USER_INFO");
    private static final dk f12030o = new dk("APP_ENVIRONMENT");
    private static final dk f12031p = new dk("APP_ENVIRONMENT_REVISION");
    private static final dk f12032q = new dk("LAST_MIGRATION_VERSION");
    private static final dk f12033r = new dk("LAST_APP_VERSION_WITH_FEATURES");
    private static final dk f12034s = new dk("APPLICATION_FEATURES");

    static {
        dk dkVar = new dk("SESSION_ALIVE_TIME");
    }

    public ca(bq bqVar) {
        super(bqVar);
    }

    public long m15456a(long j) {
        return m15410b(f12019d.m15730b(), j);
    }

    public long m15462b(long j) {
        return m15410b(f12024i.m15730b(), j);
    }

    public long m15467c(long j) {
        return m15410b(f12027l.m15730b(), j);
    }

    public long m15472d(long j) {
        return m15410b(f12028m.m15730b(), j);
    }

    public int m15455a(int i) {
        return m15409b(f12033r.m15730b(), i);
    }

    public long m15474e(long j) {
        return m15410b(f12017b.m15730b(), j);
    }

    public long m15475f(long j) {
        return m15410b(f12021f.m15730b(), j);
    }

    public long m15476g(long j) {
        return m15410b(f12018c.m15730b(), j);
    }

    public long m15477h(long j) {
        return m15410b(f12023h.m15730b(), j);
    }

    public C4304a m15457a() {
        C4304a c4304a;
        synchronized (this) {
            c4304a = new C4304a(m15411b(f12030o.m15730b(), "{}"), m15410b(f12031p.m15730b(), 0));
        }
        return c4304a;
    }

    public long m15478i(long j) {
        return m15410b(f12016a.m15730b(), j);
    }

    public long m15479j(long j) {
        return m15410b(f12022g.m15730b(), j);
    }

    public String m15466b() {
        return m15411b(f12034s.m15730b(), "");
    }

    public boolean m15461a(boolean z) {
        return m15412b(f12020e.m15730b(), z);
    }

    public C4270a m15468c() {
        return C4270a.m14230a(Long.valueOf(m15410b(f12026k.m15730b(), (long) C4270a.UNDEFINED.f11404d)).intValue());
    }

    public String m15460a(String str) {
        return m15411b(f12029n.m15730b(), str);
    }

    public ca m15480k(long j) {
        return (ca) m15406a(f12019d.m15730b(), j);
    }

    public ca m15481l(long j) {
        return (ca) m15406a(f12024i.m15730b(), j);
    }

    public ca m15459a(C4304a c4304a) {
        synchronized (this) {
            m15407a(f12030o.m15730b(), c4304a.f11564a);
            m15406a(f12031p.m15730b(), c4304a.f11565b);
        }
        return this;
    }

    public ca m15482m(long j) {
        return (ca) m15406a(f12027l.m15730b(), j);
    }

    public ca m15483n(long j) {
        return (ca) m15406a(f12028m.m15730b(), j);
    }

    public ca m15484o(long j) {
        return (ca) m15406a(f12017b.m15730b(), j);
    }

    public ca m15485p(long j) {
        return (ca) m15406a(f12021f.m15730b(), j);
    }

    public ca m15486q(long j) {
        return (ca) m15406a(f12018c.m15730b(), j);
    }

    public ca m15487r(long j) {
        return (ca) m15406a(f12023h.m15730b(), j);
    }

    public ca m15488s(long j) {
        return (ca) m15406a(f12016a.m15730b(), j);
    }

    public ca m15489t(long j) {
        return (ca) m15406a(f12022g.m15730b(), j);
    }

    public ca m15458a(C4270a c4270a) {
        return (ca) m15406a(f12026k.m15730b(), (long) c4270a.f11404d);
    }

    public ca m15464b(String str) {
        return (ca) m15407a(f12029n.m15730b(), str);
    }

    public ca m15465b(boolean z) {
        return (ca) m15408a(f12020e.m15730b(), z);
    }

    public long m15471d() {
        return m15410b(f12032q.m15730b(), 0);
    }

    public ca m15490u(long j) {
        return (ca) m15406a(f12032q.m15730b(), j);
    }

    public boolean m15470c(boolean z) {
        return m15412b(f12025j.m15730b(), z);
    }

    public ca m15473d(boolean z) {
        return (ca) m15408a(f12025j.m15730b(), z);
    }

    public ca m15463b(int i) {
        return (ca) m15405a(f12033r.m15730b(), i);
    }

    public ca m15469c(String str) {
        return (ca) m15407a(f12034s.m15730b(), str);
    }
}
