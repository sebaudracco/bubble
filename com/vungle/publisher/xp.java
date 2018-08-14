package com.vungle.publisher;

import com.vungle.publisher.env.k;
import com.vungle.publisher.env.o;
import com.vungle.publisher.env.r;
import com.vungle.publisher.log.g;
import com.vungle.publisher.yg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class xp implements MembersInjector<xg> {
    static final /* synthetic */ boolean f11303a = (!xp.class.desiredAssertionStatus());
    private final Provider<uz> f11304b;
    private final Provider<xq> f11305c;
    private final Provider<uw> f11306d;
    private final Provider<a> f11307e;
    private final Provider<xd> f11308f;
    private final Provider<r> f11309g;
    private final Provider<yd> f11310h;
    private final Provider<k> f11311i;
    private final Provider<qg> f11312j;
    private final Provider<o> f11313k;
    private final Provider<bw> f11314l;
    private final Provider<ct.a> f11315m;
    private final Provider<wd.a> f11316n;
    private final Provider<g> f11317o;

    public /* synthetic */ void injectMembers(Object obj) {
        m14135a((xg) obj);
    }

    public xp(Provider<uz> provider, Provider<xq> provider2, Provider<uw> provider3, Provider<a> provider4, Provider<xd> provider5, Provider<r> provider6, Provider<yd> provider7, Provider<k> provider8, Provider<qg> provider9, Provider<o> provider10, Provider<bw> provider11, Provider<ct.a> provider12, Provider<wd.a> provider13, Provider<g> provider14) {
        if (f11303a || provider != null) {
            this.f11304b = provider;
            if (f11303a || provider2 != null) {
                this.f11305c = provider2;
                if (f11303a || provider3 != null) {
                    this.f11306d = provider3;
                    if (f11303a || provider4 != null) {
                        this.f11307e = provider4;
                        if (f11303a || provider5 != null) {
                            this.f11308f = provider5;
                            if (f11303a || provider6 != null) {
                                this.f11309g = provider6;
                                if (f11303a || provider7 != null) {
                                    this.f11310h = provider7;
                                    if (f11303a || provider8 != null) {
                                        this.f11311i = provider8;
                                        if (f11303a || provider9 != null) {
                                            this.f11312j = provider9;
                                            if (f11303a || provider10 != null) {
                                                this.f11313k = provider10;
                                                if (f11303a || provider11 != null) {
                                                    this.f11314l = provider11;
                                                    if (f11303a || provider12 != null) {
                                                        this.f11315m = provider12;
                                                        if (f11303a || provider13 != null) {
                                                            this.f11316n = provider13;
                                                            if (f11303a || provider14 != null) {
                                                                this.f11317o = provider14;
                                                                return;
                                                            }
                                                            throw new AssertionError();
                                                        }
                                                        throw new AssertionError();
                                                    }
                                                    throw new AssertionError();
                                                }
                                                throw new AssertionError();
                                            }
                                            throw new AssertionError();
                                        }
                                        throw new AssertionError();
                                    }
                                    throw new AssertionError();
                                }
                                throw new AssertionError();
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<xg> m14134a(Provider<uz> provider, Provider<xq> provider2, Provider<uw> provider3, Provider<a> provider4, Provider<xd> provider5, Provider<r> provider6, Provider<yd> provider7, Provider<k> provider8, Provider<qg> provider9, Provider<o> provider10, Provider<bw> provider11, Provider<ct.a> provider12, Provider<wd.a> provider13, Provider<g> provider14) {
        return new xp(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
    }

    public void m14135a(xg xgVar) {
        if (xgVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        xgVar.b = (uz) this.f11304b.get();
        xgVar.c = (xq) this.f11305c.get();
        xgVar.d = (uw) this.f11306d.get();
        xgVar.e = (a) this.f11307e.get();
        xgVar.f = (xd) this.f11308f.get();
        xgVar.g = (r) this.f11309g.get();
        xgVar.h = (yd) this.f11310h.get();
        xgVar.i = (k) this.f11311i.get();
        xgVar.j = (qg) this.f11312j.get();
        xgVar.k = (o) this.f11313k.get();
        xgVar.l = (bw) this.f11314l.get();
        xgVar.m = (ct.a) this.f11315m.get();
        xgVar.n = (wd.a) this.f11316n.get();
        xgVar.o = (g) this.f11317o.get();
    }
}
