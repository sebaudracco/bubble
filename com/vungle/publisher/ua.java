package com.vungle.publisher;

import com.vungle.publisher.tw.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ua implements MembersInjector<a> {
    static final /* synthetic */ boolean f11129a = (!ua.class.desiredAssertionStatus());
    private final Provider<tw> f11130b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13966a((a) obj);
    }

    public ua(Provider<tw> provider) {
        if (f11129a || provider != null) {
            this.f11130b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13965a(Provider<tw> provider) {
        return new ua(provider);
    }

    public void m13966a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f11130b;
    }
}
