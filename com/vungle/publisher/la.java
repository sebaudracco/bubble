package com.vungle.publisher;

import com.vungle.publisher.jn.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class la implements MembersInjector<jn> {
    static final /* synthetic */ boolean f10680a = (!la.class.desiredAssertionStatus());
    private final Provider<ci> f10681b;
    private final Provider<a> f10682c;
    private final Provider<lb.a> f10683d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13605a((jn) obj);
    }

    public la(Provider<ci> provider, Provider<a> provider2, Provider<lb.a> provider3) {
        if (f10680a || provider != null) {
            this.f10681b = provider;
            if (f10680a || provider2 != null) {
                this.f10682c = provider2;
                if (f10680a || provider3 != null) {
                    this.f10683d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<jn> m13604a(Provider<ci> provider, Provider<a> provider2, Provider<lb.a> provider3) {
        return new la(provider, provider2, provider3);
    }

    public void m13605a(jn jnVar) {
        if (jnVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        jnVar.v = (ci) this.f10681b.get();
        jnVar.E = (a) this.f10682c.get();
        jnVar.F = (lb.a) this.f10683d.get();
    }
}
