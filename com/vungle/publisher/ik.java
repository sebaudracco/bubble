package com.vungle.publisher;

import com.vungle.publisher.ic.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ik implements MembersInjector<a> {
    static final /* synthetic */ boolean f10336a = (!ik.class.desiredAssertionStatus());
    private final Provider<ci> f10337b;
    private final Provider<co.a> f10338c;
    private final Provider<ct.a> f10339d;
    private final Provider<hx.a> f10340e;
    private final Provider<hr.a> f10341f;
    private final Provider<ic> f10342g;

    public /* synthetic */ void injectMembers(Object obj) {
        m13356a((a) obj);
    }

    public ik(Provider<ci> provider, Provider<co.a> provider2, Provider<ct.a> provider3, Provider<hx.a> provider4, Provider<hr.a> provider5, Provider<ic> provider6) {
        if (f10336a || provider != null) {
            this.f10337b = provider;
            if (f10336a || provider2 != null) {
                this.f10338c = provider2;
                if (f10336a || provider3 != null) {
                    this.f10339d = provider3;
                    if (f10336a || provider4 != null) {
                        this.f10340e = provider4;
                        if (f10336a || provider5 != null) {
                            this.f10341f = provider5;
                            if (f10336a || provider6 != null) {
                                this.f10342g = provider6;
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

    public static MembersInjector<a> m13355a(Provider<ci> provider, Provider<co.a> provider2, Provider<ct.a> provider3, Provider<hx.a> provider4, Provider<hr.a> provider5, Provider<ic> provider6) {
        return new ik(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public void m13356a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10337b.get();
        aVar.a = (co.a) this.f10338c.get();
        aVar.b = (ct.a) this.f10339d.get();
        aVar.c = (hx.a) this.f10340e.get();
        aVar.e = (hr.a) this.f10341f.get();
        aVar.f = this.f10342g;
    }
}
