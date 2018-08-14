package com.vungle.publisher;

import com.vungle.publisher.cn.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class xf implements MembersInjector<xd> {
    static final /* synthetic */ boolean f11298a = (!xf.class.desiredAssertionStatus());
    private final Provider<b> f11299b;
    private final Provider<eb.b> f11300c;

    public /* synthetic */ void injectMembers(Object obj) {
        m14131a((xd) obj);
    }

    public xf(Provider<b> provider, Provider<eb.b> provider2) {
        if (f11298a || provider != null) {
            this.f11299b = provider;
            if (f11298a || provider2 != null) {
                this.f11300c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<xd> m14130a(Provider<b> provider, Provider<eb.b> provider2) {
        return new xf(provider, provider2);
    }

    public void m14131a(xd xdVar) {
        if (xdVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        xdVar.a = (b) this.f11299b.get();
        xdVar.b = (eb.b) this.f11300c.get();
    }
}
