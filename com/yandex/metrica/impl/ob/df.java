package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.SharedPreferences;
import com.yandex.metrica.CounterConfiguration.C4270a;
import com.yandex.metrica.impl.C4305a.C4304a;

public class df extends dd {
    public static final dk f12161c = new dk("APP_ENVIRONMENT");
    public static final dk f12162d = new dk("APP_ENVIRONMENT_REVISION");
    private static final dk f12163e = new dk("SESSION_SLEEP_START_");
    private static final dk f12164f = new dk("SESSION_ID_");
    private static final dk f12165g = new dk("SESSION_COUNTER_ID_");
    private static final dk f12166h = new dk("SESSION_INIT_TIME_");
    private static final dk f12167i = new dk("SESSION_ALIVE_TIME_");
    private static final dk f12168j = new dk("SESSION_IS_ALIVE_REPORT_NEEDED_");
    private static final dk f12169k = new dk("BG_SESSION_ID_");
    private static final dk f12170l = new dk("BG_SESSION_SLEEP_START_");
    private static final dk f12171m = new dk("BG_SESSION_COUNTER_ID_");
    private static final dk f12172n = new dk("BG_SESSION_INIT_TIME_");
    private static final dk f12173o = new dk("COLLECT_INSTALLED_APPS_");
    private static final dk f12174p = new dk("IDENTITY_SEND_TIME_");
    private static final dk f12175q = new dk("USER_INFO_");
    private static final dk f12176r = new dk("REFERRER_");
    private static final dk f12177s = new dk("APP_ENVIRONMENT_");
    private static final dk f12178t = new dk("APP_ENVIRONMENT_REVISION_");
    private dk f12179A;
    private dk f12180B;
    private dk f12181C;
    private dk f12182D;
    private dk f12183E;
    private dk f12184F;
    private dk f12185G;
    private dk f12186H;
    private dk f12187I;
    private dk f12188J;
    private dk f12189u;
    private dk f12190v;
    private dk f12191w;
    private dk f12192x;
    private dk f12193y;
    private dk f12194z;

    public df(Context context, String str) {
        super(context, str);
        m15689d();
        m15680a(-1);
        m15684b(0);
        m15687c(0);
    }

    protected void mo7073h() {
        super.mo7073h();
        this.f12189u = new dk(f12163e.m15728a(), m15645j());
        this.f12190v = new dk(f12164f.m15728a(), m15645j());
        this.f12191w = new dk(f12165g.m15728a(), m15645j());
        this.f12192x = new dk(f12166h.m15728a(), m15645j());
        this.f12193y = new dk(f12167i.m15728a(), m15645j());
        this.f12194z = new dk(f12168j.m15728a(), m15645j());
        this.f12179A = new dk(f12169k.m15728a(), m15645j());
        this.f12180B = new dk(f12170l.m15728a(), m15645j());
        this.f12181C = new dk(f12171m.m15728a(), m15645j());
        this.f12182D = new dk(f12172n.m15728a(), m15645j());
        this.f12183E = new dk(f12174p.m15728a(), m15645j());
        this.f12184F = new dk(f12173o.m15728a(), m15645j());
        this.f12185G = new dk(f12175q.m15728a(), m15645j());
        this.f12186H = new dk(f12176r.m15728a(), m15645j());
        this.f12187I = new dk(f12177s.m15728a(), m15645j());
        this.f12188J = new dk(f12178t.m15728a(), m15645j());
    }

    protected String mo7072f() {
        return "_boundentrypreferences";
    }

    public long m15675a(long j) {
        return m15674a(this.f12192x.m15730b(), j);
    }

    public long m15681b(long j) {
        return m15674a(this.f12182D.m15730b(), j);
    }

    public long m15685c(long j) {
        return m15674a(this.f12183E.m15730b(), j);
    }

    public long m15688d(long j) {
        return m15674a(this.f12190v.m15730b(), j);
    }

    public long m15690e(long j) {
        return m15674a(this.f12179A.m15730b(), j);
    }

    public long m15692f(long j) {
        return m15674a(this.f12191w.m15730b(), j);
    }

    private long m15674a(String str, long j) {
        return this.b.getLong(str, j);
    }

    public long m15694g(long j) {
        return m15674a(this.f12181C.m15730b(), j);
    }

    public C4304a m15676a() {
        C4304a c4304a;
        synchronized (this) {
            if (this.b.contains(this.f12187I.m15730b()) && this.b.contains(this.f12188J.m15730b())) {
                c4304a = new C4304a(this.b.getString(this.f12187I.m15730b(), "{}"), this.b.getLong(this.f12188J.m15730b(), 0));
            } else {
                c4304a = null;
            }
        }
        return c4304a;
    }

    public long m15696h(long j) {
        return m15674a(this.f12189u.m15730b(), j);
    }

    public long m15698i(long j) {
        return m15674a(this.f12180B.m15730b(), j);
    }

    public Boolean m15678a(boolean z) {
        return Boolean.valueOf(this.b.getBoolean(this.f12194z.m15730b(), z));
    }

    public C4270a m15682b() {
        return C4270a.m14230a(this.b.getInt(this.f12184F.m15730b(), C4270a.UNDEFINED.f11404d));
    }

    public String m15679a(String str) {
        return this.b.getString(this.f12185G.m15730b(), str);
    }

    public String m15683b(String str) {
        return this.b.getString(this.f12186H.m15730b(), str);
    }

    public df m15677a(C4304a c4304a) {
        synchronized (this) {
            m15640a(this.f12187I.m15730b(), c4304a.f11564a);
            m15640a(this.f12188J.m15730b(), Long.valueOf(c4304a.f11565b));
        }
        return this;
    }

    public df m15686c() {
        return (df) m15642h(this.f12186H.m15730b());
    }

    public void m15680a(int i) {
        dl.m15732a(this.b, this.f12193y.m15730b(), i);
    }

    public void m15684b(int i) {
        dl.m15732a(this.b, this.f12189u.m15730b(), i);
    }

    public void m15687c(int i) {
        dl.m15732a(this.b, this.f12191w.m15730b(), i);
    }

    public void m15689d() {
        SharedPreferences sharedPreferences = this.b;
        String b = this.f12184F.m15730b();
        if (sharedPreferences != null && sharedPreferences.contains(b)) {
            try {
                sharedPreferences.getBoolean(b, false);
                sharedPreferences.edit().remove(b).putInt(b, C4270a.UNDEFINED.f11404d).apply();
            } catch (ClassCastException e) {
            }
        }
    }

    public df m15691e() {
        return (df) m15642h(this.f12184F.m15730b());
    }

    public boolean m15695g() {
        return this.b.contains(this.f12192x.m15730b()) || this.b.contains(this.f12193y.m15730b()) || this.b.contains(this.f12194z.m15730b()) || this.b.contains(this.f12189u.m15730b()) || this.b.contains(this.f12190v.m15730b()) || this.b.contains(this.f12191w.m15730b()) || this.b.contains(this.f12182D.m15730b()) || this.b.contains(this.f12180B.m15730b()) || this.b.contains(this.f12179A.m15730b()) || this.b.contains(this.f12181C.m15730b()) || this.b.contains(this.f12187I.m15730b()) || this.b.contains(this.f12184F.m15730b()) || this.b.contains(this.f12185G.m15730b()) || this.b.contains(this.f12186H.m15730b()) || this.b.contains(this.f12183E.m15730b());
    }

    public void m15699l() {
        this.b.edit().remove(this.f12182D.m15730b()).remove(this.f12181C.m15730b()).remove(this.f12179A.m15730b()).remove(this.f12180B.m15730b()).remove(this.f12192x.m15730b()).remove(this.f12191w.m15730b()).remove(this.f12190v.m15730b()).remove(this.f12189u.m15730b()).remove(this.f12194z.m15730b()).remove(this.f12193y.m15730b()).remove(this.f12185G.m15730b()).remove(this.f12184F.m15730b()).remove(this.f12187I.m15730b()).remove(this.f12188J.m15730b()).remove(this.f12186H.m15730b()).remove(this.f12183E.m15730b()).apply();
    }
}
