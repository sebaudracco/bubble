package com.vungle.publisher;

import com.vungle.publisher.jn.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kz implements MembersInjector<a> {
    static final /* synthetic */ boolean f10662a = (!kz.class.desiredAssertionStatus());
    private final Provider<ci> f10663b;
    private final Provider<qg> f10664c;
    private final Provider<zl> f10665d;
    private final Provider<ct.a> f10666e;
    private final Provider<jn> f10667f;
    private final Provider<dk.a> f10668g;
    private final Provider<String> f10669h;
    private final Provider<jt.a> f10670i;
    private final Provider<jo.a> f10671j;
    private final Provider<jy.a> f10672k;
    private final Provider<ks.a> f10673l;
    private final Provider<iz.a> f10674m;
    private final Provider<dw.a> f10675n;

    public /* synthetic */ void injectMembers(Object obj) {
        m13601a((a) obj);
    }

    public kz(Provider<ci> provider, Provider<qg> provider2, Provider<zl> provider3, Provider<ct.a> provider4, Provider<jn> provider5, Provider<dk.a> provider6, Provider<String> provider7, Provider<jt.a> provider8, Provider<jo.a> provider9, Provider<jy.a> provider10, Provider<ks.a> provider11, Provider<iz.a> provider12, Provider<dw.a> provider13) {
        if (f10662a || provider != null) {
            this.f10663b = provider;
            if (f10662a || provider2 != null) {
                this.f10664c = provider2;
                if (f10662a || provider3 != null) {
                    this.f10665d = provider3;
                    if (f10662a || provider4 != null) {
                        this.f10666e = provider4;
                        if (f10662a || provider5 != null) {
                            this.f10667f = provider5;
                            if (f10662a || provider6 != null) {
                                this.f10668g = provider6;
                                if (f10662a || provider7 != null) {
                                    this.f10669h = provider7;
                                    if (f10662a || provider8 != null) {
                                        this.f10670i = provider8;
                                        if (f10662a || provider9 != null) {
                                            this.f10671j = provider9;
                                            if (f10662a || provider10 != null) {
                                                this.f10672k = provider10;
                                                if (f10662a || provider11 != null) {
                                                    this.f10673l = provider11;
                                                    if (f10662a || provider12 != null) {
                                                        this.f10674m = provider12;
                                                        if (f10662a || provider13 != null) {
                                                            this.f10675n = provider13;
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

    public static MembersInjector<a> m13600a(Provider<ci> provider, Provider<qg> provider2, Provider<zl> provider3, Provider<ct.a> provider4, Provider<jn> provider5, Provider<dk.a> provider6, Provider<String> provider7, Provider<jt.a> provider8, Provider<jo.a> provider9, Provider<jy.a> provider10, Provider<ks.a> provider11, Provider<iz.a> provider12, Provider<dw.a> provider13) {
        return new kz(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
    }

    public void m13601a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10663b.get();
        aVar.a = (qg) this.f10664c.get();
        aVar.b = (zl) this.f10665d.get();
        aVar.c = (ct.a) this.f10666e.get();
        aVar.e = this.f10667f;
        aVar.f = (zl) this.f10665d.get();
        aVar.g = (dk.a) this.f10668g.get();
        aVar.h = this.f10669h;
        aVar.i = (jt.a) this.f10670i.get();
        aVar.j = (jo.a) this.f10671j.get();
        aVar.k = (jy.a) this.f10672k.get();
        aVar.l = (ks.a) this.f10673l.get();
        aVar.m = (iz.a) this.f10674m.get();
        aVar.n = (dw.a) this.f10675n.get();
    }
}
