package com.vungle.publisher;

import com.vungle.publisher.lb.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class le implements MembersInjector<a> {
    static final /* synthetic */ boolean f10688a = (!le.class.desiredAssertionStatus());
    private final Provider<lb> f10689b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13611a((a) obj);
    }

    public le(Provider<lb> provider) {
        if (f10688a || provider != null) {
            this.f10689b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13610a(Provider<lb> provider) {
        return new le(provider);
    }

    public void m13611a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10689b;
    }
}
