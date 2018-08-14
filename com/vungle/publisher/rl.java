package com.vungle.publisher;

import com.vungle.publisher.env.i;
import com.vungle.publisher.rv.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rl implements MembersInjector<rg> {
    static final /* synthetic */ boolean f10972a = (!rl.class.desiredAssertionStatus());
    private final Provider<qg> f10973b;
    private final Provider<rr> f10974c;
    private final Provider<a> f10975d;
    private final Provider<bz> f10976e;
    private final Provider<i> f10977f;
    private final Provider<jn.a> f10978g;

    public /* synthetic */ void injectMembers(Object obj) {
        m13879a((rg) obj);
    }

    public rl(Provider<qg> provider, Provider<rr> provider2, Provider<a> provider3, Provider<bz> provider4, Provider<i> provider5, Provider<jn.a> provider6) {
        if (f10972a || provider != null) {
            this.f10973b = provider;
            if (f10972a || provider2 != null) {
                this.f10974c = provider2;
                if (f10972a || provider3 != null) {
                    this.f10975d = provider3;
                    if (f10972a || provider4 != null) {
                        this.f10976e = provider4;
                        if (f10972a || provider5 != null) {
                            this.f10977f = provider5;
                            if (f10972a || provider6 != null) {
                                this.f10978g = provider6;
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

    public static MembersInjector<rg> m13878a(Provider<qg> provider, Provider<rr> provider2, Provider<a> provider3, Provider<bz> provider4, Provider<i> provider5, Provider<jn.a> provider6) {
        return new rl(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public void m13879a(rg rgVar) {
        if (rgVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        rgVar.b = (qg) this.f10973b.get();
        rgVar.c = (rr) this.f10974c.get();
        rgVar.d = (a) this.f10975d.get();
        rgVar.e = (bz) this.f10976e.get();
        rgVar.f = (i) this.f10977f.get();
        rgVar.g = (jn.a) this.f10978g.get();
    }
}
