package com.vungle.publisher;

import com.vungle.publisher.rz.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rt implements MembersInjector<rr> {
    static final /* synthetic */ boolean f11001a = (!rt.class.desiredAssertionStatus());
    private final Provider<mv> f11002b;
    private final Provider<rb> f11003c;
    private final Provider<a> f11004d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13892a((rr) obj);
    }

    public rt(Provider<mv> provider, Provider<rb> provider2, Provider<a> provider3) {
        if (f11001a || provider != null) {
            this.f11002b = provider;
            if (f11001a || provider2 != null) {
                this.f11003c = provider2;
                if (f11001a || provider3 != null) {
                    this.f11004d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<rr> m13891a(Provider<mv> provider, Provider<rb> provider2, Provider<a> provider3) {
        return new rt(provider, provider2, provider3);
    }

    public void m13892a(rr rrVar) {
        if (rrVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        rrVar.a = (mv) this.f11002b.get();
        rrVar.b = (rb) this.f11003c.get();
        rrVar.c = (a) this.f11004d.get();
    }
}
