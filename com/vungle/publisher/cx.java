package com.vungle.publisher;

import com.vungle.publisher.ct.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cx implements MembersInjector<ct> {
    static final /* synthetic */ boolean f9900a = (!cx.class.desiredAssertionStatus());
    private final Provider<ci> f9901b;
    private final Provider<a> f9902c;

    public /* synthetic */ void injectMembers(Object obj) {
        m12986a((ct) obj);
    }

    public cx(Provider<ci> provider, Provider<a> provider2) {
        if (f9900a || provider != null) {
            this.f9901b = provider;
            if (f9900a || provider2 != null) {
                this.f9902c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ct> m12985a(Provider<ci> provider, Provider<a> provider2) {
        return new cx(provider, provider2);
    }

    public void m12986a(ct ctVar) {
        if (ctVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ctVar.v = (ci) this.f9901b.get();
        ctVar.c = (a) this.f9902c.get();
    }
}
