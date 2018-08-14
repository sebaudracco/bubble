package com.vungle.publisher;

import com.vungle.publisher.nk.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nr implements MembersInjector<a> {
    static final /* synthetic */ boolean f10819a = (!nr.class.desiredAssertionStatus());
    private final Provider<qg> f10820b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13758a((a) obj);
    }

    public nr(Provider<qg> provider) {
        if (f10819a || provider != null) {
            this.f10820b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13757a(Provider<qg> provider) {
        return new nr(provider);
    }

    public void m13758a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.eventBus = (qg) this.f10820b.get();
    }
}
