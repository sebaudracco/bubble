package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ud implements MembersInjector<ub> {
    static final /* synthetic */ boolean f11133a = (!ud.class.desiredAssertionStatus());
    private final Provider<ue> f11134b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13970a((ub) obj);
    }

    public ud(Provider<ue> provider) {
        if (f11133a || provider != null) {
            this.f11134b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ub> m13969a(Provider<ue> provider) {
        return new ud(provider);
    }

    public void m13970a(ub ubVar) {
        if (ubVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ubVar.a = (ue) this.f11134b.get();
    }
}
