package com.vungle.publisher;

import com.vungle.publisher.my.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nc implements MembersInjector<a> {
    static final /* synthetic */ boolean f10782a = (!nc.class.desiredAssertionStatus());
    private final Provider<my> f10783b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13736a((a) obj);
    }

    public nc(Provider<my> provider) {
        if (f10782a || provider != null) {
            this.f10783b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13735a(Provider<my> provider) {
        return new nc(provider);
    }

    public void m13736a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10783b;
    }
}
