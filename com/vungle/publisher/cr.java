package com.vungle.publisher;

import com.vungle.publisher.co.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cr implements MembersInjector<a> {
    static final /* synthetic */ boolean f9887a = (!cr.class.desiredAssertionStatus());
    private final Provider<ci> f9888b;
    private final Provider<co> f9889c;

    public /* synthetic */ void injectMembers(Object obj) {
        m12976a((a) obj);
    }

    public cr(Provider<ci> provider, Provider<co> provider2) {
        if (f9887a || provider != null) {
            this.f9888b = provider;
            if (f9887a || provider2 != null) {
                this.f9889c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m12975a(Provider<ci> provider, Provider<co> provider2) {
        return new cr(provider, provider2);
    }

    public void m12976a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f9888b.get();
        aVar.a = this.f9889c;
    }
}
