package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class uy implements MembersInjector<uw> {
    static final /* synthetic */ boolean f11165a = (!uy.class.desiredAssertionStatus());
    private final Provider<uj> f11166b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13999a((uw) obj);
    }

    public uy(Provider<uj> provider) {
        if (f11165a || provider != null) {
            this.f11166b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<uw> m13998a(Provider<uj> provider) {
        return new uy(provider);
    }

    public void m13999a(uw uwVar) {
        if (uwVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        uwVar.a = (uj) this.f11166b.get();
    }
}
