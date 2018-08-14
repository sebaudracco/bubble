package com.vungle.publisher;

import com.vungle.publisher.li.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ll implements MembersInjector<a> {
    static final /* synthetic */ boolean f10699a = (!ll.class.desiredAssertionStatus());
    private final Provider<li> f10700b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13626a((a) obj);
    }

    public ll(Provider<li> provider) {
        if (f10699a || provider != null) {
            this.f10700b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13625a(Provider<li> provider) {
        return new ll(provider);
    }

    public void m13626a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10700b;
    }
}
