package com.vungle.publisher;

import com.vungle.publisher.py.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class qd implements MembersInjector<a> {
    static final /* synthetic */ boolean f10935a = (!qd.class.desiredAssertionStatus());
    private final Provider<py> f10936b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13833a((a) obj);
    }

    public qd(Provider<py> provider) {
        if (f10935a || provider != null) {
            this.f10936b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13832a(Provider<py> provider) {
        return new qd(provider);
    }

    public void m13833a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10936b;
    }
}
