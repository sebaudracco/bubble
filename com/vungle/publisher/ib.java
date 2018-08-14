package com.vungle.publisher;

import com.vungle.publisher.hx.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ib implements MembersInjector<hx> {
    static final /* synthetic */ boolean f10319a = (!ib.class.desiredAssertionStatus());
    private final Provider<ci> f10320b;
    private final Provider<a> f10321c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13342a((hx) obj);
    }

    public ib(Provider<ci> provider, Provider<a> provider2) {
        if (f10319a || provider != null) {
            this.f10320b = provider;
            if (f10319a || provider2 != null) {
                this.f10321c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<hx> m13341a(Provider<ci> provider, Provider<a> provider2) {
        return new ib(provider, provider2);
    }

    public void m13342a(hx hxVar) {
        if (hxVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        hxVar.v = (ci) this.f10320b.get();
        hxVar.e = (a) this.f10321c.get();
    }
}
