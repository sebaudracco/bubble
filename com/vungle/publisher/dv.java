package com.vungle.publisher;

import com.vungle.publisher.ds.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dv implements MembersInjector<a> {
    static final /* synthetic */ boolean f9964a = (!dv.class.desiredAssertionStatus());
    private final Provider<ds> f9965b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13074a((a) obj);
    }

    public dv(Provider<ds> provider) {
        if (f9964a || provider != null) {
            this.f9965b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13073a(Provider<ds> provider) {
        return new dv(provider);
    }

    public void m13074a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f9965b;
    }
}
