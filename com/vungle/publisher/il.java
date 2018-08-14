package com.vungle.publisher;

import com.vungle.publisher.ic.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class il implements MembersInjector<ic> {
    static final /* synthetic */ boolean f10343a = (!il.class.desiredAssertionStatus());
    private final Provider<ci> f10344b;
    private final Provider<zl> f10345c;
    private final Provider<a> f10346d;
    private final Provider<hx.a> f10347e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13358a((ic) obj);
    }

    public il(Provider<ci> provider, Provider<zl> provider2, Provider<a> provider3, Provider<hx.a> provider4) {
        if (f10343a || provider != null) {
            this.f10344b = provider;
            if (f10343a || provider2 != null) {
                this.f10345c = provider2;
                if (f10343a || provider3 != null) {
                    this.f10346d = provider3;
                    if (f10343a || provider4 != null) {
                        this.f10347e = provider4;
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

    public static MembersInjector<ic> m13357a(Provider<ci> provider, Provider<zl> provider2, Provider<a> provider3, Provider<hx.a> provider4) {
        return new il(provider, provider2, provider3, provider4);
    }

    public void m13358a(ic icVar) {
        if (icVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        icVar.v = (ci) this.f10344b.get();
        icVar.r = (zl) this.f10345c.get();
        icVar.s = (a) this.f10346d.get();
        icVar.w = (hx.a) this.f10347e.get();
    }
}
