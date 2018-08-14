package com.vungle.publisher;

import com.vungle.publisher.wj.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wl implements MembersInjector<a> {
    static final /* synthetic */ boolean f11238a = (!wl.class.desiredAssertionStatus());
    private final Provider<C4238m.a> f11239b;

    public /* synthetic */ void injectMembers(Object obj) {
        m14070a((a) obj);
    }

    public wl(Provider<C4238m.a> provider) {
        if (f11238a || provider != null) {
            this.f11239b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m14069a(Provider<C4238m.a> provider) {
        return new wl(provider);
    }

    public void m14070a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (C4238m.a) this.f11239b.get();
    }
}
