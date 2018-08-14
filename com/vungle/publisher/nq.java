package com.vungle.publisher;

import com.vungle.publisher.nk.a.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nq implements MembersInjector<a> {
    static final /* synthetic */ boolean f10817a = (!nq.class.desiredAssertionStatus());
    private final Provider<nk.a> f10818b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13756a((a) obj);
    }

    public nq(Provider<nk.a> provider) {
        if (f10817a || provider != null) {
            this.f10818b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13755a(Provider<nk.a> provider) {
        return new nq(provider);
    }

    public void m13756a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (nk.a) this.f10818b.get();
    }
}
