package com.vungle.publisher;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import com.vungle.publisher.bw.b;
import com.vungle.publisher.c.1;
import com.vungle.publisher.c.2;
import com.vungle.publisher.cn.c;
import com.vungle.publisher.eb.C1603b;
import com.vungle.publisher.env.C1612k;
import com.vungle.publisher.env.C1613o;
import com.vungle.publisher.env.C1614r;
import com.vungle.publisher.env.i;
import com.vungle.publisher.hr.C1632a;
import com.vungle.publisher.log.C1654g;
import com.vungle.publisher.log.Logger;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import rx.Observable;

@Singleton
/* compiled from: vungle */
public class C1591c extends pi {
    @Inject
    C1614r f2731a;
    @Inject
    Context f2732b;
    @Inject
    i f2733c;
    @Inject
    qg f2734d;
    @Inject
    Class f2735e;
    @Inject
    Class f2736f;
    @Inject
    Class f2737g;
    @Inject
    bw f2738h;
    @Inject
    sz f2739i;
    @Inject
    Lazy<C1589a> f2740j;
    @Inject
    Provider<C1590b> f2741k;
    @Inject
    vc f2742l;
    @Inject
    C1613o f2743m;
    @Inject
    C1674u f2744n;
    @Inject
    C1612k f2745o;
    @Inject
    C1603b f2746p;
    @Inject
    xg f2747q;
    @Inject
    C1654g f2748r;

    @Singleton
    /* compiled from: vungle */
    static class C1589a extends pi {
        final String f2724a = Logger.PREPARE_TAG;
        @Inject
        C1654g f2725b;

        @Inject
        C1589a() {
        }

        public void onEvent(av<cn> startPlayAdEvent) {
            try {
                startPlayAdEvent.b().b(c.f);
            } catch (Exception e) {
                Logger.e(Logger.PREPARE_TAG, "could not start Ad play: " + e.getLocalizedMessage());
                this.f2725b.f3084b.severe("error processing start play ad event: " + e.getMessage());
            }
        }

        public void onEvent(aj event) {
            this.f2725b.f3084b.info("sent ad report - un-registering play ad listener");
            unregister();
        }

        public void onEvent(bk event) {
            this.f2725b.f3084b.info("play ad failure - un-registering play ad listener");
            unregister();
        }
    }

    /* compiled from: vungle */
    static class C1590b extends pi {
        volatile boolean f2726a;
        volatile hr f2727b;
        final long f2728c = System.currentTimeMillis();
        @Inject
        C1632a f2729d;
        @Inject
        C1654g f2730e;

        @Inject
        C1590b() {
        }

        void m3484a() {
            this.f2726a = true;
            synchronized (this) {
                notifyAll();
            }
        }

        public void onEvent(ak prepareStreamingAdFailureEvent) {
            unregister();
            this.f2730e.f3084b.info("request streaming ad failure after " + (prepareStreamingAdFailureEvent.e() - this.f2728c) + " ms");
            m3484a();
        }

        public void onEvent(au prepareStreamingAdSuccessEvent) {
            unregister();
            long e = prepareStreamingAdSuccessEvent.e() - this.f2728c;
            wm wmVar = (wm) prepareStreamingAdSuccessEvent.a();
            if (Boolean.TRUE.equals(wmVar.n())) {
                String g = wmVar.g();
                this.f2730e.f3084b.info("received streaming ad: " + g + " after " + e + " ms");
                hr hrVar = (hr) this.f2729d.a(g, true);
                if (hrVar == null) {
                    hr a = this.f2729d.m4124a(wmVar);
                    this.f2727b = a;
                    this.f2730e.f3084b.info("inserting new " + a.B());
                    try {
                        a.mo2982w();
                    } catch (SQLException e2) {
                        this.f2730e.f3084b.info("did not insert streaming ad - possible duplicate");
                    }
                } else {
                    try {
                        this.f2729d.a(hrVar, wmVar);
                    } catch (Exception e3) {
                        this.f2730e.f3084b.warning("error updating ad " + g + ": " + e3.getMessage());
                    }
                    c g2 = hrVar.g();
                    switch (2.a[g2.ordinal()]) {
                        case 1:
                            this.f2730e.f3084b.warning("unexpected ad status " + g2 + " for " + hrVar.B());
                            break;
                        case 2:
                        case 3:
                            break;
                        default:
                            this.f2730e.f3084b.warning("existing " + hrVar.B() + " with " + "status" + " " + g2 + " - ignoring");
                            break;
                    }
                    this.f2730e.f3084b.info("existing " + hrVar.B() + " with " + "status" + " " + g2);
                    if (g2 != c.e) {
                        hrVar.b(c.e);
                    }
                    this.f2727b = hrVar;
                }
            } else {
                this.f2730e.f3084b.info("no streaming ad to play after " + e + " ms");
            }
            m3484a();
        }
    }

    @Inject
    C1591c() {
    }

    public void m3488a() {
        register();
        this.f2731a.m3941a(this.f2743m.m3934d());
        if (this.f2731a.m3950c(this.f2743m.m3935e()) == 0) {
            m3493b(this.f2743m.m3935e(), true);
        } else {
            this.f2738h.m3479b(C1597d.m3668a(this), b.s, 5000);
        }
    }

    /* synthetic */ void m3499e() {
        this.f2734d.m4568a(new bl(this.f2743m.m3935e()));
    }

    public boolean m3491a(String str) {
        if (str == null || this.f2746p.m3744b(str) == null) {
            return false;
        }
        return true;
    }

    public boolean m3494b(String str) {
        this.f2748r.f3084b.info("isAdPlayable called for placement: " + str);
        return !this.f2745o.m3908a() && this.f2745o.m3910b() && m3491a(str);
    }

    public cn m3495c(String str) {
        cn m_;
        String str2 = null;
        dr d = m3497d(str);
        if (d != null) {
            m_ = d.m_();
        } else {
            m_ = null;
        }
        cn a = m3487a(str, m_ == null ? null : m_.l());
        if (a == null) {
            a = m_;
        }
        java.util.logging.Logger logger = this.f2748r.f3084b;
        StringBuilder append = new StringBuilder().append("next ad ");
        if (a != null) {
            str2 = a.B();
        }
        logger.info(append.append(str2).toString());
        return a;
    }

    void m3490a(Observable<dr<?>> observable, String str) {
        this.f2748r.f3084b.info("requestLocalAd processing: " + str);
        if (this.f2731a.m3946a(false, true)) {
            this.f2731a.m3947b(str);
            observable.subscribe(new 1(this, str));
            return;
        }
        this.f2748r.f3084b.info("queuing this for now, will get back to this Ad Prepare");
        this.f2731a.m3942a(str);
    }

    dr<?> m3486a(String str, boolean z) {
        dr<?> drVar = null;
        if (this.f2733c.l()) {
            dr<?> a;
            if (z) {
                a = this.f2746p.m3741a(str);
            } else {
                a = this.f2746p.m3744b(str);
            }
            if (a == null) {
                this.f2748r.f3084b.info("no local ad available");
                if (!z) {
                    return a;
                }
                m3490a(this.f2747q.m4850a(str), str);
                return a;
            }
            c g = a.g();
            if (g != c.d) {
                if (g == c.e) {
                    this.f2748r.f3084b.info("local ad already available for " + a.d());
                }
                drVar = a;
            } else if (z) {
                this.f2748r.f3084b.info("local ad partially prepared, restarting preparation for " + a.d());
                m3490a(this.f2747q.m4848a((dr) a), str);
            } else {
                this.f2748r.f3084b.info("local ad partially prepared, but not restarting preparation for " + a.d());
            }
            return drVar;
        }
        this.f2748r.f3084b.warning("unable to fetch local ad -  no external storage available");
        return null;
    }

    public dr<?> m3497d(String str) {
        return m3486a(str, false);
    }

    public void m3489a(String str, t tVar) {
        Object obj = null;
        cn cnVar = null;
        if (this.f2743m.m3923b(str) == null) {
            this.f2734d.m4568a(new bm(null, str));
        } else if (this.f2745o.m3909a(str)) {
            this.f2748r.f3084b.fine("AdManager.playAd()");
            try {
                cnVar = m3495c(str);
                if (cnVar == null) {
                    this.f2748r.f3084b.info("no ad to play");
                    this.f2734d.m4568a(new bq(null, str));
                } else {
                    Class cls;
                    ((C1589a) this.f2740j.get()).register();
                    if (cnVar.a_() == m.a || cnVar.a_() == m.b) {
                        cls = this.f2735e;
                    } else if (cnVar.a_() == m.c && x.b == x.a(cnVar.s)) {
                        cls = this.f2737g;
                    } else {
                        cls = this.f2736f;
                    }
                    Intent intent = new Intent(this.f2732b, cls);
                    intent.addFlags(805306368);
                    this.f2744n.m4691a(intent, tVar);
                    intent.putExtra(VungleAdActivity.AD_ID_EXTRA_KEY, (String) cnVar.c_());
                    intent.putExtra(VungleAdActivity.AD_TYPE_EXTRA_KEY, cnVar.a_());
                    intent.putExtra(VungleAdActivity.AD_PLACEMENT_REFERENCE_ID_KEY, str);
                    this.f2732b.startActivity(intent);
                    obj = 1;
                }
                if (obj != null) {
                }
            } catch (Exception e) {
                this.f2748r.f3084b.severe("Error launching ad: " + e.getMessage());
                this.f2734d.m4568a(new bu(cnVar, str, false));
            } finally {
                this.f2742l.m4738c();
            }
        }
    }

    hr m3487a(String str, String str2) {
        Throwable th;
        hr hrVar;
        Throwable th2;
        hr hrVar2;
        hr hrVar3 = null;
        try {
            if (!this.f2743m.m3929b()) {
                return null;
            }
            this.f2748r.f3084b.info("requesting streaming ad");
            C1590b c1590b = (C1590b) this.f2741k.get();
            c1590b.register();
            this.f2742l.m4731a(str, str2);
            long j = c1590b.f2728c;
            long c = ((long) this.f2743m.m3931c()) + j;
            synchronized (c1590b) {
                while (!c1590b.f2726a) {
                    try {
                        long currentTimeMillis = c - System.currentTimeMillis();
                        if (currentTimeMillis > 0) {
                            try {
                                c1590b.wait(currentTimeMillis);
                            } catch (InterruptedException e) {
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        hrVar = null;
                        th2 = th;
                    }
                }
                c = System.currentTimeMillis() - j;
                if (c1590b.f2726a) {
                    hrVar = c1590b.f2727b;
                    if (hrVar != null) {
                        try {
                            this.f2748r.f3084b.info("request streaming ad success after " + c + " ms " + hrVar.B());
                            hrVar3 = hrVar;
                        } catch (Throwable th4) {
                            th2 = th4;
                            try {
                                throw th2;
                            } catch (Throwable e2) {
                                th2 = e2;
                                hrVar2 = hrVar;
                            }
                        }
                    } else {
                        hrVar3 = hrVar;
                    }
                } else {
                    this.f2748r.f3084b.info("request streaming ad timeout after " + c + " ms");
                    c1590b.m3484a();
                }
                try {
                    return hrVar3;
                } catch (Throwable th32) {
                    th = th32;
                    hrVar = hrVar3;
                    th2 = th;
                    throw th2;
                }
            }
        } catch (Throwable e22) {
            th = e22;
            hrVar2 = null;
            th2 = th;
            Logger.e(Logger.EVENT_TAG, "error getting streaming ad", th2);
            this.f2748r.f3084b.severe("error getting streaming ad: " + th2.getMessage());
            return hrVar2;
        }
    }

    void m3492b() {
        this.f2738h.m3471a(b.l);
        Long c = this.f2746p.m3746c();
        if (c != null) {
            this.f2738h.m3475a(C1602e.m3736a(this), b.l, c.longValue());
        }
    }

    /* synthetic */ void m3498d() {
        this.f2746p.m3739a();
    }

    void m3493b(String str, boolean z) {
        m3486a(str, z);
        m3492b();
    }

    public void onEvent(ql event) {
        m3493b(event.a(), true);
    }

    public void onEvent(al event) {
        this.f2748r.f3084b.finest("HandleQueuedAdAvailabilityEvent");
        if (this.f2731a.m3964n()) {
            m3485f();
        } else {
            m3493b(this.f2731a.m3963m(), true);
        }
    }

    private void m3485f() {
        this.f2738h.m3472a(C1619f.m3975a(this));
    }

    /* synthetic */ void m3496c() {
        this.f2748r.f3084b.finest("cleanUpInactivePlacements");
        this.f2746p.m3740a(this.f2743m.m3936f());
    }

    public void onEvent(ag decreasedAdAvailabilityEvent) {
        this.f2748r.f3084b.finest("decreasedAdAvailabilityEvent: " + decreasedAdAvailabilityEvent.a());
        if (decreasedAdAvailabilityEvent.a().equals(this.f2743m.m3935e())) {
            m3493b(this.f2743m.m3935e(), true);
        }
    }

    public void m3500e(String str) {
        if (this.f2743m.m3923b(str) == null) {
            this.f2734d.m4568a(new bl(str));
        } else if (m3491a(str)) {
            this.f2734d.m4568a(new as(str));
        } else if (this.f2731a.m3950c(str) > 0) {
            this.f2734d.m4568a(new bl(str));
        } else if (!str.equals(this.f2731a.m3965o())) {
            m3486a(str, true);
        }
    }

    public boolean m3501f(String str) {
        if (this.f2743m.m3923b(str) != null) {
            dr c = this.f2746p.m3745c(str);
            if (c != null) {
                cn m_ = c.m_();
                if (m_.a_() == m.c && x.b == x.a(m_.s)) {
                    this.f2734d.m4568a(new sf(str));
                    return true;
                }
            }
        }
        return false;
    }
}
