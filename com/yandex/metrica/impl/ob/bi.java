package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.C4372h;
import com.yandex.metrica.impl.C4511p.C4510a;

public class bi {
    private be f11926a;
    private bc f11927b;
    private C4503t f11928c;

    public bi(C4503t c4503t, ca caVar) {
        this.f11928c = c4503t;
        this.f11926a = new be(c4503t, new bf(caVar));
        this.f11927b = new bc(c4503t, new bd(caVar));
    }

    public void m15282a() {
        this.f11926a.m15234i();
    }

    public void m15285b() {
        this.f11926a.m15233h();
        this.f11927b.m15233h();
    }

    public boolean m15284a(C4372h c4372h) {
        return m15279a(c4372h, this.f11926a, this.f11927b);
    }

    public boolean m15286b(C4372h c4372h) {
        if (this.f11926a.m15231f()) {
            return false;
        }
        return m15279a(c4372h, this.f11927b, this.f11926a);
    }

    private boolean m15279a(C4372h c4372h, bg bgVar, bg bgVar2) {
        if (bgVar.m15231f()) {
            bgVar.m15234i();
            return false;
        }
        if (bgVar.m15236k()) {
            this.f11928c.m16145a(C4372h.m15048a(c4372h, C4510a.EVENT_TYPE_ALIVE), m15281a(bgVar));
            bgVar.m15226a(false);
        } else if (bgVar2.m15236k()) {
            this.f11928c.m16145a(C4372h.m15048a(c4372h, C4510a.EVENT_TYPE_ALIVE), m15281a(bgVar2));
            bgVar2.m15226a(false);
        }
        bgVar2.m15233h();
        bgVar.m15229d();
        return true;
    }

    public void m15283a(boolean z) {
        m15280f().m15226a(z);
    }

    public long[] m15287c() {
        return new long[]{this.f11927b.m15228c(), this.f11926a.m15228c()};
    }

    public bj m15288d() {
        bg f = m15280f();
        return new bj().m15291a(f.m15228c()).m15293b(f.m15235j()).m15296c(f.m15232g()).m15292a(f.mo7044a());
    }

    private bg m15280f() {
        return this.f11926a.m15231f() ? this.f11926a : this.f11927b;
    }

    bj m15281a(bg bgVar) {
        return new bj().m15291a(bgVar.m15228c()).m15292a(bgVar.mo7044a()).m15293b(bgVar.m15235j()).m15296c(bgVar.m15230e());
    }

    public bj m15289e() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        this.f11928c.m16165i().m15351a(currentTimeMillis, bl.BACKGROUND);
        return new bj().m15291a(currentTimeMillis).m15292a(bl.BACKGROUND).m15293b(0).m15296c(0);
    }
}
