package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class sd implements MembersInjector<rz> {
    static final /* synthetic */ boolean f11050a = (!sd.class.desiredAssertionStatus());
    private final Provider<mv> f11051b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13906a((rz) obj);
    }

    public sd(Provider<mv> provider) {
        if (f11050a || provider != null) {
            this.f11051b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<rz> m13905a(Provider<mv> provider) {
        return new sd(provider);
    }

    public void m13906a(rz rzVar) {
        if (rzVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        rzVar.p = (mv) this.f11051b.get();
    }
}
