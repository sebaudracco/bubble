package com.vungle.publisher;

import com.vungle.publisher.env.i;
import com.vungle.publisher.env.o;
import com.vungle.publisher.yn.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nv implements MembersInjector<ns> {
    static final /* synthetic */ boolean f10823a = (!nv.class.desiredAssertionStatus());
    private final Provider<qg> f10824b;
    private final Provider<zc> f10825c;
    private final Provider<i> f10826d;
    private final Provider<a> f10827e;
    private final Provider<my.a> f10828f;
    private final Provider<o> f10829g;
    private final Provider<mv> f10830h;
    private final Provider<ns.a.a> f10831i;

    public /* synthetic */ void injectMembers(Object obj) {
        m13762a((ns) obj);
    }

    public nv(Provider<qg> provider, Provider<zc> provider2, Provider<i> provider3, Provider<a> provider4, Provider<my.a> provider5, Provider<o> provider6, Provider<mv> provider7, Provider<ns.a.a> provider8) {
        if (f10823a || provider != null) {
            this.f10824b = provider;
            if (f10823a || provider2 != null) {
                this.f10825c = provider2;
                if (f10823a || provider3 != null) {
                    this.f10826d = provider3;
                    if (f10823a || provider4 != null) {
                        this.f10827e = provider4;
                        if (f10823a || provider5 != null) {
                            this.f10828f = provider5;
                            if (f10823a || provider6 != null) {
                                this.f10829g = provider6;
                                if (f10823a || provider7 != null) {
                                    this.f10830h = provider7;
                                    if (f10823a || provider8 != null) {
                                        this.f10831i = provider8;
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

    public static MembersInjector<ns> m13761a(Provider<qg> provider, Provider<zc> provider2, Provider<i> provider3, Provider<a> provider4, Provider<my.a> provider5, Provider<o> provider6, Provider<mv> provider7, Provider<ns.a.a> provider8) {
        return new nv(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public void m13762a(ns nsVar) {
        if (nsVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        nsVar.i = (qg) this.f10824b.get();
        nsVar.j = (zc) this.f10825c.get();
        nsVar.k = (i) this.f10826d.get();
        nsVar.n = (a) this.f10827e.get();
        nsVar.o = (my.a) this.f10828f.get();
        nsVar.p = (o) this.f10829g.get();
        nsVar.q = (mv) this.f10830h.get();
        nsVar.r = (ns.a.a) this.f10831i.get();
    }
}
