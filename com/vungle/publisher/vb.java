package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class vb implements MembersInjector<uz> {
    static final /* synthetic */ boolean f11171a = (!vb.class.desiredAssertionStatus());
    private final Provider<ue> f11172b;

    public /* synthetic */ void injectMembers(Object obj) {
        m14005a((uz) obj);
    }

    public vb(Provider<ue> provider) {
        if (f11171a || provider != null) {
            this.f11172b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<uz> m14004a(Provider<ue> provider) {
        return new vb(provider);
    }

    public void m14005a(uz uzVar) {
        if (uzVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        uzVar.a = (ue) this.f11172b.get();
    }
}
