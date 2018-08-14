package com.yandex.metrica.impl.ob;

public abstract class cb {
    private final bq f11996a;
    private final String f11997b;

    static {
        cb.class.getSimpleName();
    }

    public cb(bq bqVar) {
        this(bqVar, null);
    }

    public cb(bq bqVar, String str) {
        this.f11996a = bqVar;
        this.f11997b = str;
        mo7060f();
    }

    protected void mo7060f() {
    }

    public String m15414g() {
        return this.f11997b;
    }

    protected dk m15416q(String str) {
        return new dk(str, m15414g());
    }

    protected <T extends cb> T m15407a(String str, String str2) {
        synchronized (this) {
            this.f11996a.m15386b(str, str2);
        }
        return this;
    }

    protected <T extends cb> T m15406a(String str, long j) {
        synchronized (this) {
            this.f11996a.m15385b(str, j);
        }
        return this;
    }

    protected <T extends cb> T m15405a(String str, int i) {
        synchronized (this) {
            this.f11996a.m15384b(str, i);
        }
        return this;
    }

    protected <T extends cb> T m15408a(String str, boolean z) {
        synchronized (this) {
            this.f11996a.m15387b(str, z);
        }
        return this;
    }

    protected <T extends cb> T m15417r(String str) {
        synchronized (this) {
            this.f11996a.m15380a(str);
        }
        return this;
    }

    public void m15415h() {
        synchronized (this) {
            this.f11996a.m15388b();
        }
    }

    long m15410b(String str, long j) {
        return this.f11996a.m15379a(str, j);
    }

    int m15409b(String str, int i) {
        return this.f11996a.m15378a(str, i);
    }

    String m15411b(String str, String str2) {
        return this.f11996a.m15382a(str, str2);
    }

    boolean m15412b(String str, boolean z) {
        return this.f11996a.m15383a(str, z);
    }
}
