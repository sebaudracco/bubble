package com.yandex.metrica.impl.ob;

public class by extends cb {
    private final dk f11998a = new dk("init_event_pref_key");
    private final dk f11999b = new dk("first_event_pref_key");
    private final dk f12000c = new dk("first_event_description_key");

    public by(bq bqVar) {
        super(bqVar);
    }

    public void m15419a() {
        m15407a(this.f11998a.m15730b(), "DONE").m15415h();
    }

    public void m15421b() {
        m15407a(this.f11999b.m15730b(), "DONE").m15415h();
    }

    public String m15418a(String str) {
        return m15411b(this.f11998a.m15730b(), str);
    }

    public String m15420b(String str) {
        return m15411b(this.f11999b.m15730b(), str);
    }

    public boolean m15423c() {
        return m15418a(null) != null;
    }

    public boolean m15425d() {
        return m15420b(null) != null;
    }

    public void m15422c(String str) {
        m15407a(this.f12000c.m15730b(), str).m15415h();
    }

    public String m15424d(String str) {
        return m15411b(this.f12000c.m15730b(), str);
    }

    public void m15426e() {
        m15417r(this.f12000c.m15730b()).m15415h();
    }
}
