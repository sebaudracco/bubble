package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.CounterConfiguration.C4270a;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C4305a;
import com.yandex.metrica.impl.C4372h;
import com.yandex.metrica.impl.C4511p.C4510a;
import com.yandex.metrica.impl.ad;
import com.yandex.metrica.impl.ba;
import com.yandex.metrica.impl.bj;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.utils.C4529j;
import java.util.concurrent.Executor;

public class C4503t implements C4502u {
    private boolean f12473a;
    private boolean f12474b;
    private final HandlerThread f12475c;
    private final Handler f12476d;
    private final Context f12477e;
    private final C4493r f12478f;
    private ca f12479g;
    private cc f12480h;
    private by f12481i;
    private cd f12482j;
    private CounterConfiguration f12483k;
    private final ba f12484l;
    private bj f12485m;
    private bn f12486n;
    private C4390v f12487o;
    private C4305a f12488p;
    private C4492q f12489q;
    private long f12490r;
    private long f12491s;
    private int f12492t;
    private int f12493u;
    private volatile bi f12494v;
    private final C4529j f12495w;
    private Runnable f12496x;

    class C45011 implements Runnable {
        final /* synthetic */ C4503t f12472a;

        C45011(C4503t c4503t) {
            this.f12472a = c4503t;
        }

        public void run() {
            this.f12472a.m16153c();
        }
    }

    public bi m16141a() {
        return this.f12494v;
    }

    public C4503t(Context context, Executor executor, C4493r c4493r, CounterConfiguration counterConfiguration, C4492q c4492q) {
        this(context, executor, c4493r, counterConfiguration, c4492q, new ba());
    }

    C4503t(Context context, Executor executor, C4493r c4493r, CounterConfiguration counterConfiguration, C4492q c4492q, ba baVar) {
        this.f12473a = false;
        this.f12474b = false;
        this.f12495w = new C4529j();
        this.f12496x = new C45011(this);
        this.f12484l = baVar;
        this.f12477e = context.getApplicationContext();
        this.f12478f = c4493r;
        this.f12483k = counterConfiguration;
        if (m16129J()) {
            bq b = bp.m15358a(this.f12477e).m15365b(mo7113l());
            this.f12479g = new ca(b);
            this.f12481i = new by(b);
        }
        bp a = bp.m15358a(this.f12477e);
        this.f12480h = new cc(a.m15364b());
        this.f12482j = new cd(a.m15367d(), mo7113l().m16105b());
        int libraryApiLevel = YandexMetrica.getLibraryApiLevel();
        if (m16129J() && this.f12479g.m15471d() < ((long) libraryApiLevel)) {
            new C4500s(this, new db(m16135D())).m16121a();
            this.f12479g.m15490u((long) libraryApiLevel).m15415h();
        }
        if (m16129J()) {
            this.f12486n = new bn(this, bp.m15358a(this.f12477e).m15362a(mo7113l()));
            this.f12490r = this.f12479g.m15467c(0);
            this.f12491s = this.f12479g.m15472d(0);
            this.f12492t = this.f12479g.m15455a(-1);
            this.f12493u = bk.m14990b(context, c4493r.m16105b());
            this.f12494v = new bi(this, this.f12479g);
            this.f12489q = c4492q;
            this.f12488p = this.f12489q.m16099a(this, this.f12479g);
            if (mo7116p().m16247b()) {
                mo7116p().m16244a("Read app environment for component %s. Value: %s", mo7113l().toString(), this.f12488p.m14452b().f11564a);
            }
        }
        this.f12475c = new HandlerThread("TaskHandler [" + c4493r.m16105b() + "]");
        this.f12475c.start();
        this.f12476d = new Handler(this.f12475c.getLooper());
        this.f12484l.m14813a(this);
        this.f12485m = new bj(this, executor);
        if (this.f12486n != null) {
            this.f12486n.m15354a((C4502u) this);
        }
        this.f12487o = new ab(new C4507y(this));
    }

    public void m16144a(C4372h c4372h) {
        if (mo7116p().m16247b()) {
            mo7116p().m16283a(c4372h, "Event received on service");
        }
        if (bk.m14992b(this.f12484l.m14807a())) {
            this.f12484l.m14825c(this);
            this.f12487o.m15139a(c4372h);
        }
    }

    public void m16150b(C4372h c4372h) {
        this.f12487o.m15139a(c4372h);
    }

    private boolean m16129J() {
        return !this.f12478f.m16106c();
    }

    public void m16154c(C4372h c4372h) {
        m16131b(c4372h, this.f12494v.m15289e());
    }

    public void m16156d(C4372h c4372h) {
        if (this.f12494v.m15286b(c4372h)) {
            if (this.f12481i.m15425d()) {
                m16131b(C4372h.m15048a(c4372h, C4510a.EVENT_TYPE_START), this.f12494v.m15288d());
            } else if (c4372h.m15062c() == C4510a.EVENT_TYPE_FIRST_ACTIVATION.m16188a()) {
                m16131b(c4372h, this.f12494v.m15288d());
                m16131b(C4372h.m15048a(c4372h, C4510a.EVENT_TYPE_START), this.f12494v.m15288d());
                return;
            }
        }
        m16131b(c4372h, this.f12494v.m15288d());
    }

    private void m16131b(C4372h c4372h, bj bjVar) {
        if (TextUtils.isEmpty(c4372h.m15077k())) {
            c4372h.mo7030a(m16161g());
        }
        this.f12486n.m15353a(c4372h, bjVar, this.f12488p.m14452b());
        this.f12485m.m14970b();
    }

    public synchronized void m16143a(CounterConfiguration counterConfiguration) {
        this.f12483k = counterConfiguration;
        this.f12484l.m14833e(this);
    }

    public void mo7110b() {
        if (((this.f12486n.m15346a() >= ((long) this.f12483k.m14256c()) ? 1 : 0) | this.f12473a) != 0) {
            m16159f();
            this.f12473a = false;
        }
    }

    public synchronized void m16153c() {
        this.f12474b = true;
        bk.m14980a(this.f12485m);
        bk.m14980a(this.f12486n);
        this.f12476d.removeCallbacksAndMessages(null);
        this.f12475c.quit();
    }

    public void m16155d() {
        this.f12476d.postDelayed(this.f12496x, ad.f11577a);
    }

    public synchronized void m16157e() {
        this.f12485m.m14971c();
    }

    public synchronized void m16159f() {
        this.f12485m.m14969a();
    }

    public String m16161g() {
        return this.f12479g.m15460a(null);
    }

    public ba mo7111h() {
        return this.f12484l;
    }

    public bn m16165i() {
        return this.f12486n;
    }

    public CounterConfiguration mo7112j() {
        return this.f12483k;
    }

    public ResultReceiver m16167k() {
        return this.f12483k != null ? this.f12483k.m14239a() : null;
    }

    public C4493r mo7113l() {
        return this.f12478f;
    }

    public Context mo7114m() {
        return this.f12477e;
    }

    public Handler m16170n() {
        return this.f12476d;
    }

    public synchronized boolean mo7115o() {
        return this.f12474b;
    }

    public void m16142a(C4270a c4270a) {
        this.f12479g.m15458a(c4270a).m15415h();
        if (this.f12477e.getPackageName().equals(this.f12478f.m16105b())) {
            this.f12480h.m15494a(c4270a).m15415h();
        }
    }

    public C4529j mo7116p() {
        if (!(this.f12495w.m16247b() || this.f12483k == null || !this.f12483k.m14286s())) {
            this.f12495w.m16240a();
        }
        return this.f12495w;
    }

    public void m16158e(C4372h c4372h) {
        m16152b(true);
        m16156d(c4372h);
        m16178v();
    }

    public void m16160f(C4372h c4372h) {
        m16156d(c4372h);
        m16130K();
    }

    public void m16162g(C4372h c4372h) {
        m16156d(c4372h);
        m16179w();
    }

    public void m16173q() {
        m16130K();
    }

    public void m16174r() {
        m16179w();
    }

    public void m16164h(C4372h c4372h) {
        this.f12488p.m14450a(c4372h.m15076j());
        if (this.f12489q.m16100a(this.f12488p.m14452b(), this.f12479g) && mo7116p().m16247b()) {
            mo7116p().m16244a("Save new app environment for %s. Value: %s", mo7113l(), r0.f11564a);
        }
    }

    public void m16175s() {
        this.f12488p.m14449a();
        this.f12489q.m16101b(this.f12488p.m14452b(), this.f12479g);
    }

    public void m16146a(String str) {
        this.f12479g.m15464b(str).m15415h();
    }

    public void m16151b(String str) {
        this.f12480h.m15499b(str).m15415h();
        this.f12482j.m15539s(str).m15415h();
    }

    public String m16176t() {
        return this.f12480h.m15495a(null);
    }

    public void m16177u() {
        this.f12480h.m15497b().m15415h();
    }

    void m16179w() {
        this.f12492t = bk.m14990b(this.f12477e, this.f12477e.getPackageName());
        this.f12479g.m15463b(this.f12492t);
    }

    boolean m16181y() {
        return this.f12492t < this.f12493u;
    }

    public boolean m16182z() {
        boolean z;
        if ((System.currentTimeMillis() / 1000) - this.f12490r > bh.f11923a) {
            z = true;
        } else {
            z = false;
        }
        return z && mo7111h().m14805M();
    }

    public boolean m16132A() {
        return mo7112j().m14291x() && m16180x() && mo7111h().m14802J() && mo7111h().m14805M();
    }

    public boolean m16133B() {
        return m16181y() && mo7111h().m14803K() && mo7111h().m14805M();
    }

    public by m16134C() {
        return this.f12481i;
    }

    public dc m16135D() {
        return new dc(this.f12477e, this.f12478f.m16104a());
    }

    public cd m16136E() {
        return this.f12482j;
    }

    public ca m16137F() {
        return this.f12479g;
    }

    public boolean m16138G() {
        C4270a a = this.f12480h.m15492a();
        C4270a c = this.f12479g.m15468c();
        if (a == C4270a.TRUE && c == C4270a.TRUE) {
            return true;
        }
        return false;
    }

    public void m16147a(boolean z) {
        this.f12482j.m15518e(z).m15415h();
    }

    public boolean m16139H() {
        return (this.f12483k.m14290w() && this.f12482j.m15517d()) ? false : true;
    }

    public void m16152b(boolean z) {
        this.f12473a = z;
    }

    public void m16149b(CounterConfiguration counterConfiguration) {
        this.f12483k.m14245a(counterConfiguration);
    }

    public ca m16140I() {
        return this.f12479g;
    }

    public void m16145a(C4372h c4372h, bj bjVar) {
        m16131b(C4372h.m15048a(c4372h, C4510a.EVENT_TYPE_ALIVE), bjVar);
    }

    public void m16178v() {
        this.f12490r = System.currentTimeMillis() / 1000;
        this.f12479g.m15482m(this.f12490r).m15415h();
    }

    private void m16130K() {
        this.f12491s = System.currentTimeMillis() / 1000;
        this.f12479g.m15483n(this.f12491s).m15415h();
    }

    boolean m16180x() {
        return (System.currentTimeMillis() / 1000) - this.f12491s > bh.f11924b;
    }
}
