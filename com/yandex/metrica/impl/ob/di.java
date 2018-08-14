package com.yandex.metrica.impl.ob;

import android.content.Context;

public class di extends dd {
    static final dk f12201c = new dk("PREF_KEY_DEVICE_ID_");
    static final dk f12202d = new dk("PREF_KEY_UID_");
    static final dk f12203e = new dk("STARTUP_CLIDS_MATCH_WITH_APP_CLIDS_");
    static final dk f12204f = new dk("PREF_KEY_PINNING_UPDATE_URL");
    private static final dk f12205g = new dk("PREF_KEY_HOST_URL_");
    private static final dk f12206h = new dk("PREF_KEY_REPORT_URL_");
    private static final dk f12207i = new dk("PREF_KEY_GET_AD_URL");
    private static final dk f12208j = new dk("PREF_KEY_REPORT_AD_URL");
    private static final dk f12209k = new dk("PREF_KEY_STARTUP_OBTAIN_TIME_");
    private static final dk f12210l = new dk("PREF_KEY_STARTUP_ENCODED_CLIDS_");
    private static final dk f12211m = new dk("PREF_KEY_DISTRIBUTION_REFERRER_");
    private static final dk f12212n = new dk("PREF_KEY_EASY_COLLECTING_ENABLED_");
    private dk f12213o;
    private dk f12214p;
    private dk f12215q;
    private dk f12216r;
    private dk f12217s;
    private dk f12218t;
    private dk f12219u;
    private dk f12220v;
    private dk f12221w;
    private dk f12222x;

    public di(Context context) {
        this(context, null);
    }

    public di(Context context, String str) {
        super(context, str);
    }

    protected void mo7073h() {
        super.mo7073h();
        this.f12213o = new dk(f12201c.m15728a());
        this.f12214p = new dk(f12202d.m15728a(), m15645j());
        this.f12215q = new dk(f12205g.m15728a(), m15645j());
        this.f12216r = new dk(f12206h.m15728a(), m15645j());
        this.f12217s = new dk(f12207i.m15728a(), m15645j());
        this.f12218t = new dk(f12208j.m15728a(), m15645j());
        this.f12219u = new dk(f12209k.m15728a(), m15645j());
        this.f12220v = new dk(f12210l.m15728a(), m15645j());
        this.f12221w = new dk(f12211m.m15728a(), m15645j());
        this.f12222x = new dk(f12212n.m15728a(), m15645j());
    }

    protected String mo7072f() {
        return "_startupserviceinfopreferences";
    }

    public long m15710a(long j) {
        return this.b.getLong(this.f12219u.m15730b(), j);
    }

    public String m15712a(String str) {
        return this.b.getString(this.f12213o.m15730b(), str);
    }

    public String m15713b(String str) {
        return this.b.getString(this.f12214p.m15730b(), str);
    }

    public String m15715c(String str) {
        return this.b.getString(this.f12215q.m15730b(), str);
    }

    public String m15716d(String str) {
        return this.b.getString(this.f12220v.m15730b(), str);
    }

    public String m15717e(String str) {
        return this.b.getString(this.f12216r.m15730b(), str);
    }

    public String m15719f(String str) {
        return this.b.getString(this.f12217s.m15730b(), str);
    }

    public String m15720g(String str) {
        return this.b.getString(this.f12218t.m15730b(), str);
    }

    public String m15711a() {
        return this.b.getString(this.f12221w.m15728a(), null);
    }

    public di m15722i(String str) {
        return (di) m15640a(this.f12214p.m15730b(), str);
    }

    public di m15723j(String str) {
        return (di) m15640a(this.f12213o.m15730b(), str);
    }

    public static void m15709b(Context context) {
        dl.m15731a(context, "_startupserviceinfopreferences").edit().remove(f12201c.m15728a()).apply();
    }

    public void m15714b() {
        m15642h(this.f12213o.m15730b()).m15642h(this.f12214p.m15730b()).m15642h(this.f12215q.m15730b()).m15642h(this.f12216r.m15730b()).m15642h(this.f12217s.m15730b()).m15642h(this.f12218t.m15730b()).m15642h(this.f12219u.m15730b()).m15642h(this.f12222x.m15730b()).m15642h(this.f12220v.m15730b()).m15642h(this.f12221w.m15728a()).m15642h(f12203e.m15728a()).m15642h(f12204f.m15728a()).m15646k();
    }
}
