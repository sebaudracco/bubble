package com.vungle.publisher;

import com.vungle.publisher.yn.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class yq implements MembersInjector<a> {
    static final /* synthetic */ boolean f11361a = (!yq.class.desiredAssertionStatus());
    private final Provider<yn> f11362b;

    public /* synthetic */ void injectMembers(Object obj) {
        m14169a((a) obj);
    }

    public yq(Provider<yn> provider) {
        if (f11361a || provider != null) {
            this.f11362b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m14168a(Provider<yn> provider) {
        return new yq(provider);
    }

    public void m14169a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (yn) this.f11362b.get();
    }
}
