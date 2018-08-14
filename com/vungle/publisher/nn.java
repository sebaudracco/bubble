package com.vungle.publisher;

import com.vungle.publisher.env.i;
import com.vungle.publisher.env.o;
import com.vungle.publisher.yn.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nn implements MembersInjector<nk> {
    static final /* synthetic */ boolean f10805a = (!nn.class.desiredAssertionStatus());
    private final Provider<qg> f10806b;
    private final Provider<zc> f10807c;
    private final Provider<i> f10808d;
    private final Provider<a> f10809e;
    private final Provider<my.a> f10810f;
    private final Provider<o> f10811g;
    private final Provider<nk.a.a> f10812h;

    public /* synthetic */ void injectMembers(Object obj) {
        m13750a((nk) obj);
    }

    public nn(Provider<qg> provider, Provider<zc> provider2, Provider<i> provider3, Provider<a> provider4, Provider<my.a> provider5, Provider<o> provider6, Provider<nk.a.a> provider7) {
        if (f10805a || provider != null) {
            this.f10806b = provider;
            if (f10805a || provider2 != null) {
                this.f10807c = provider2;
                if (f10805a || provider3 != null) {
                    this.f10808d = provider3;
                    if (f10805a || provider4 != null) {
                        this.f10809e = provider4;
                        if (f10805a || provider5 != null) {
                            this.f10810f = provider5;
                            if (f10805a || provider6 != null) {
                                this.f10811g = provider6;
                                if (f10805a || provider7 != null) {
                                    this.f10812h = provider7;
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

    public static MembersInjector<nk> m13749a(Provider<qg> provider, Provider<zc> provider2, Provider<i> provider3, Provider<a> provider4, Provider<my.a> provider5, Provider<o> provider6, Provider<nk.a.a> provider7) {
        return new nn(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public void m13750a(nk nkVar) {
        if (nkVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        nkVar.i = (qg) this.f10806b.get();
        nkVar.j = (zc) this.f10807c.get();
        nkVar.k = (i) this.f10808d.get();
        nkVar.n = (a) this.f10809e.get();
        nkVar.o = (my.a) this.f10810f.get();
        nkVar.p = (o) this.f10811g.get();
        nkVar.q = (nk.a.a) this.f10812h.get();
    }
}
