package com.vungle.publisher;

import com.vungle.publisher.ys.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class yv implements MembersInjector<a> {
    static final /* synthetic */ boolean f11375a = (!yv.class.desiredAssertionStatus());
    private final Provider<ys> f11376b;

    public /* synthetic */ void injectMembers(Object obj) {
        m14177a((a) obj);
    }

    public yv(Provider<ys> provider) {
        if (f11375a || provider != null) {
            this.f11376b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m14176a(Provider<ys> provider) {
        return new yv(provider);
    }

    public void m14177a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (ys) this.f11376b.get();
    }
}
