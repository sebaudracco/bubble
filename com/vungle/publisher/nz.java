package com.vungle.publisher;

import com.vungle.publisher.ns.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nz implements MembersInjector<a> {
    static final /* synthetic */ boolean f10838a = (!nz.class.desiredAssertionStatus());
    private final Provider<qg> f10839b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13770a((a) obj);
    }

    public nz(Provider<qg> provider) {
        if (f10838a || provider != null) {
            this.f10839b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13769a(Provider<qg> provider) {
        return new nz(provider);
    }

    public void m13770a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.eventBus = (qg) this.f10839b.get();
    }
}
