package com.vungle.publisher;

import com.vungle.publisher.ts.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class tv implements MembersInjector<a> {
    static final /* synthetic */ boolean f11123a = (!tv.class.desiredAssertionStatus());
    private final Provider<ts> f11124b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13960a((a) obj);
    }

    public tv(Provider<ts> provider) {
        if (f11123a || provider != null) {
            this.f11124b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13959a(Provider<ts> provider) {
        return new tv(provider);
    }

    public void m13960a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f11124b;
    }
}
