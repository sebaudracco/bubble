package com.vungle.publisher;

import com.vungle.publisher.ns.a.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ny implements MembersInjector<a> {
    static final /* synthetic */ boolean f10836a = (!ny.class.desiredAssertionStatus());
    private final Provider<ns.a> f10837b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13768a((a) obj);
    }

    public ny(Provider<ns.a> provider) {
        if (f10836a || provider != null) {
            this.f10837b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13767a(Provider<ns.a> provider) {
        return new ny(provider);
    }

    public void m13768a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (ns.a) this.f10837b.get();
    }
}
