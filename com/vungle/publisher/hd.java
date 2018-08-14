package com.vungle.publisher;

import com.vungle.publisher.gv.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hd implements MembersInjector<a> {
    static final /* synthetic */ boolean f10246a = (!hd.class.desiredAssertionStatus());
    private final Provider<ci> f10247b;
    private final Provider<co.a> f10248c;
    private final Provider<ct.a> f10249d;
    private final Provider<gk.a> f10250e;
    private final Provider<gq.a> f10251f;
    private final Provider<gv> f10252g;

    public /* synthetic */ void injectMembers(Object obj) {
        m13291a((a) obj);
    }

    public hd(Provider<ci> provider, Provider<co.a> provider2, Provider<ct.a> provider3, Provider<gk.a> provider4, Provider<gq.a> provider5, Provider<gv> provider6) {
        if (f10246a || provider != null) {
            this.f10247b = provider;
            if (f10246a || provider2 != null) {
                this.f10248c = provider2;
                if (f10246a || provider3 != null) {
                    this.f10249d = provider3;
                    if (f10246a || provider4 != null) {
                        this.f10250e = provider4;
                        if (f10246a || provider5 != null) {
                            this.f10251f = provider5;
                            if (f10246a || provider6 != null) {
                                this.f10252g = provider6;
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

    public static MembersInjector<a> m13290a(Provider<ci> provider, Provider<co.a> provider2, Provider<ct.a> provider3, Provider<gk.a> provider4, Provider<gq.a> provider5, Provider<gv> provider6) {
        return new hd(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public void m13291a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10247b.get();
        aVar.a = (co.a) this.f10248c.get();
        aVar.b = (ct.a) this.f10249d.get();
        aVar.c = (gk.a) this.f10250e.get();
        aVar.e = (gq.a) this.f10251f.get();
        aVar.f = this.f10252g;
    }
}
