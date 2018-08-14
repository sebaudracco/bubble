package com.yandex.metrica.impl.ob;

import com.yandex.metrica.CounterConfiguration.C4270a;

public class cc extends cb {
    static final dk f12035a = new dk("LOCATION_TRACKING_ENABLED");
    static final dk f12036b = new dk("COLLECT_INSTALLED_APPS");
    static final dk f12037c = new dk("REFERRER");
    static final dk f12038d = new dk("PREF_KEY_OFFSET");
    private static final dk f12039e = new dk("LAST_MIGRATION_VERSION");

    public cc(bq bqVar) {
        super(bqVar);
    }

    public C4270a m15492a() {
        return C4270a.m14230a(Long.valueOf(m15410b(f12036b.m15730b(), (long) C4270a.UNDEFINED.f11404d)).intValue());
    }

    public String m15495a(String str) {
        return m15411b(f12037c.m15730b(), str);
    }

    public cc m15494a(C4270a c4270a) {
        return (cc) m15406a(f12036b.m15730b(), (long) c4270a.f11404d);
    }

    public cc m15499b(String str) {
        return (cc) m15407a(f12037c.m15730b(), str);
    }

    public cc m15497b() {
        return (cc) m15417r(f12037c.m15730b());
    }

    public int m15491a(int i) {
        return m15409b(f12039e.m15730b(), i);
    }

    public cc m15498b(int i) {
        return (cc) m15405a(f12039e.m15730b(), i);
    }

    public void m15496a(boolean z) {
        m15408a(f12035a.m15730b(), z).m15415h();
    }

    public boolean m15501c() {
        return m15412b(f12035a.m15730b(), false);
    }

    public long m15500c(int i) {
        return m15410b(f12038d.m15730b(), (long) i);
    }

    public cc m15493a(long j) {
        return (cc) m15406a(f12038d.m15730b(), j);
    }
}
