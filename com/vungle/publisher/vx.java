package com.vungle.publisher;

import com.vungle.publisher.vu.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class vx implements MembersInjector<a> {
    static final /* synthetic */ boolean f11191a = (!vx.class.desiredAssertionStatus());
    private final Provider<vu> f11192b;

    public /* synthetic */ void injectMembers(Object obj) {
        m14015a((a) obj);
    }

    public vx(Provider<vu> provider) {
        if (f11191a || provider != null) {
            this.f11192b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m14014a(Provider<vu> provider) {
        return new vx(provider);
    }

    public void m14015a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f11192b;
    }
}
