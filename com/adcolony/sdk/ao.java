package com.adcolony.sdk;

import com.adcolony.sdk.aa.C0595a;
import com.adcolony.sdk.az.C0639a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import org.json.JSONObject;

class ao implements Runnable {
    final long f620a = 30000;
    final long f621b = 5000;
    final int f622c = 17;
    final int f623d = 15000;
    final int f624e = 1000;
    private long f625f;
    private long f626g;
    private long f627h;
    private long f628i;
    private long f629j;
    private long f630k;
    private long f631l;
    private long f632m;
    private boolean f633n = true;
    private boolean f634o = true;
    private boolean f635p;
    private boolean f636q;
    private boolean f637r;
    private boolean f638s;
    private boolean f639t;
    private boolean f640u;
    private boolean f641v;

    class C06121 implements ah {
        final /* synthetic */ ao f619a;

        C06121(ao aoVar) {
            this.f619a = aoVar;
        }

        public void mo1863a(af afVar) {
            this.f619a.f640u = true;
        }
    }

    ao() {
    }

    public void m756a() {
        C0594a.m609a("SessionInfo.stopped", new C06121(this));
    }

    public void run() {
        while (!this.f638s) {
            this.f628i = System.currentTimeMillis();
            C0594a.m616f();
            if (this.f626g >= 30000) {
                new C0595a().m622a("Ending session due to excessive suspend time: ").m619a((double) this.f626g).m624a(aa.f480d);
                break;
            }
            if (this.f633n) {
                if (this.f635p && this.f634o) {
                    this.f635p = false;
                    this.f639t = false;
                    m763d();
                }
                this.f626g = 0;
            } else {
                if (this.f635p && !this.f634o) {
                    this.f635p = false;
                    m761c();
                }
                if (!this.f639t && C0594a.m614d() && C0594a.m613c().isFinishing()) {
                    this.f639t = true;
                    this.f630k = 0;
                }
                if (this.f639t) {
                    this.f630k += this.f627h;
                    if (this.f630k > 5000) {
                        new C0595a().m622a("Ending session due to excessive time between an Activity finishing and an onResume() event.").m624a(aa.f480d);
                        break;
                    }
                }
                this.f626g += this.f627h;
            }
            this.f627h = 17;
            m757a(this.f627h);
            this.f629j = System.currentTimeMillis() - this.f628i;
            if (this.f629j > 0 && this.f629j < 6000) {
                this.f625f += this.f629j;
            }
            C0740l a = C0594a.m605a();
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f632m > 15000 && a.m1248B()) {
                a.m1281k().m514g();
                this.f632m = currentTimeMillis;
            }
            if (C0594a.m614d() && currentTimeMillis - this.f631l > 1000) {
                this.f631l = currentTimeMillis;
                String c = a.f1297e.m716c();
                if (!c.equals(a.m1292v())) {
                    a.m1258a(c);
                    JSONObject a2 = C0802y.m1453a();
                    C0802y.m1462a(a2, "network_type", a.m1292v());
                    new af("Network.on_status_change", 1, a2).m695b();
                }
            }
        }
        new C0595a().m622a("AdColony session ending, releasing Activity reference.").m624a(aa.f479c);
        C0594a.m605a().m1267b(true);
        C0594a.m606a(null);
        this.f637r = true;
        this.f641v = true;
        m759b();
        C0639a c0639a = new C0639a(10.0d);
        while (!this.f640u && !c0639a.m866b() && this.f641v) {
            C0594a.m616f();
            m757a(100);
        }
        new C0595a().m622a("SessionInfo.stopped message received, ending ADC.update_module() spam.").m624a(aa.f480d);
    }

    void m758a(boolean z) {
        if (!this.f636q) {
            if (this.f637r) {
                C0594a.m605a().m1267b(false);
                this.f637r = false;
            }
            this.f625f = 0;
            this.f626g = 0;
            this.f636q = true;
            this.f633n = true;
            this.f640u = false;
            new Thread(this).start();
            if (z) {
                JSONObject a = C0802y.m1453a();
                C0802y.m1462a(a, "id", az.m895e());
                new af("SessionInfo.on_start", 1, a).m695b();
            }
            if (AdColony.f368a.isShutdown()) {
                AdColony.f368a = Executors.newSingleThreadExecutor();
            }
            ac.m653a();
        }
    }

    void m759b() {
        this.f636q = false;
        this.f633n = false;
        if (ac.f510l != null) {
            ac.f510l.m720a();
        }
        ac.m662b();
        JSONObject a = C0802y.m1453a();
        C0802y.m1460a(a, "session_length", ((double) this.f625f) / 1000.0d);
        new af("SessionInfo.on_stop", 1, a).m695b();
        C0594a.m616f();
        AdColony.f368a.shutdown();
        new C0595a().m622a("SESSION STOP").m624a(aa.f480d);
    }

    void m761c() {
        m760b(false);
    }

    void m760b(boolean z) {
        ArrayList c = C0594a.m605a().m1287q().m708c();
        synchronized (c) {
            Iterator it = c.iterator();
            while (it.hasNext()) {
                ai aiVar = (ai) it.next();
                JSONObject a = C0802y.m1453a();
                C0802y.m1465a(a, "from_window_focus", z);
                new af("SessionInfo.on_pause", aiVar.mo1841a(), a).m695b();
            }
        }
        this.f634o = true;
        C0594a.m616f();
    }

    void m763d() {
        m762c(false);
    }

    void m762c(boolean z) {
        ArrayList c = C0594a.m605a().m1287q().m708c();
        synchronized (c) {
            Iterator it = c.iterator();
            while (it.hasNext()) {
                ai aiVar = (ai) it.next();
                JSONObject a = C0802y.m1453a();
                C0802y.m1465a(a, "from_window_focus", z);
                new af("SessionInfo.on_resume", aiVar.mo1841a(), a).m695b();
            }
        }
        ac.m653a();
        this.f634o = false;
    }

    void m764d(boolean z) {
        this.f633n = z;
    }

    void m765e(boolean z) {
        this.f635p = z;
    }

    void m767f(boolean z) {
        this.f641v = z;
    }

    boolean m766e() {
        return this.f633n;
    }

    boolean m768f() {
        return this.f635p;
    }

    boolean m769g() {
        return this.f636q;
    }

    void m757a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
        }
    }
}
