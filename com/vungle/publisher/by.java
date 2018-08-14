package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class by implements MembersInjector<bw> {
    static final /* synthetic */ boolean f9824a = (!by.class.desiredAssertionStatus());
    private final Provider<zl> f9825b;

    public /* synthetic */ void injectMembers(Object obj) {
        m12904a((bw) obj);
    }

    public by(Provider<zl> provider) {
        if (f9824a || provider != null) {
            this.f9825b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<bw> m12903a(Provider<zl> provider) {
        return new by(provider);
    }

    public void m12904a(bw bwVar) {
        if (bwVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bwVar.a = (zl) this.f9825b.get();
    }
}
