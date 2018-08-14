package com.vungle.publisher;

import com.vungle.publisher.dw.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dz implements MembersInjector<a> {
    static final /* synthetic */ boolean f9970a = (!dz.class.desiredAssertionStatus());
    private final Provider<dw> f9971b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13080a((a) obj);
    }

    public dz(Provider<dw> provider) {
        if (f9970a || provider != null) {
            this.f9971b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13079a(Provider<dw> provider) {
        return new dz(provider);
    }

    public void m13080a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f9971b;
    }
}
