package com.vungle.publisher;

import com.vungle.publisher.oy.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class pe implements MembersInjector<a> {
    static final /* synthetic */ boolean f10917a = (!pe.class.desiredAssertionStatus());
    private final Provider<qg> f10918b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13819a((a) obj);
    }

    public pe(Provider<qg> provider) {
        if (f10917a || provider != null) {
            this.f10918b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13818a(Provider<qg> provider) {
        return new pe(provider);
    }

    public void m13819a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.eventBus = (qg) this.f10918b.get();
    }
}
