package com.vungle.publisher;

import com.vungle.publisher.gw.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ha implements MembersInjector<gw> {
    static final /* synthetic */ boolean f10239a = (!ha.class.desiredAssertionStatus());
    private final Provider<ci> f10240b;
    private final Provider<a> f10241c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13285a((gw) obj);
    }

    public ha(Provider<ci> provider, Provider<a> provider2) {
        if (f10239a || provider != null) {
            this.f10240b = provider;
            if (f10239a || provider2 != null) {
                this.f10241c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<gw> m13284a(Provider<ci> provider, Provider<a> provider2) {
        return new ha(provider, provider2);
    }

    public void m13285a(gw gwVar) {
        if (gwVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gwVar.v = (ci) this.f10240b.get();
        gwVar.e = (a) this.f10241c.get();
    }
}
