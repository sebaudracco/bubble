package com.vungle.publisher;

import com.vungle.publisher.co.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cs implements MembersInjector<co> {
    static final /* synthetic */ boolean f9890a = (!cs.class.desiredAssertionStatus());
    private final Provider<ci> f9891b;
    private final Provider<a> f9892c;

    public /* synthetic */ void injectMembers(Object obj) {
        m12978a((co) obj);
    }

    public cs(Provider<ci> provider, Provider<a> provider2) {
        if (f9890a || provider != null) {
            this.f9891b = provider;
            if (f9890a || provider2 != null) {
                this.f9892c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<co> m12977a(Provider<ci> provider, Provider<a> provider2) {
        return new cs(provider, provider2);
    }

    public void m12978a(co coVar) {
        if (coVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        coVar.v = (ci) this.f9891b.get();
        coVar.a = (a) this.f9892c.get();
    }
}
