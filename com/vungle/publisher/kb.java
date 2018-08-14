package com.vungle.publisher;

import com.vungle.publisher.jy.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kb implements MembersInjector<a> {
    static final /* synthetic */ boolean f10606a = (!kb.class.desiredAssertionStatus());
    private final Provider<ci> f10607b;
    private final Provider<jy> f10608c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13561a((a) obj);
    }

    public kb(Provider<ci> provider, Provider<jy> provider2) {
        if (f10606a || provider != null) {
            this.f10607b = provider;
            if (f10606a || provider2 != null) {
                this.f10608c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13560a(Provider<ci> provider, Provider<jy> provider2) {
        return new kb(provider, provider2);
    }

    public void m13561a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10607b.get();
        aVar.a = this.f10608c;
    }
}
