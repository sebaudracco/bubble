package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4262w implements MembersInjector<u> {
    static final /* synthetic */ boolean f11193a = (!C4262w.class.desiredAssertionStatus());
    private final Provider<AdConfig> f11194b;

    public /* synthetic */ void injectMembers(Object obj) {
        m14023a((u) obj);
    }

    public C4262w(Provider<AdConfig> provider) {
        if (f11193a || provider != null) {
            this.f11194b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<u> m14022a(Provider<AdConfig> provider) {
        return new C4262w(provider);
    }

    public void m14023a(u uVar) {
        if (uVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        uVar.a = (AdConfig) this.f11194b.get();
    }
}
