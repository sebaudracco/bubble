package com.vungle.publisher;

import com.vungle.publisher.ob.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class oe implements MembersInjector<a> {
    static final /* synthetic */ boolean f10844a = (!oe.class.desiredAssertionStatus());
    private final Provider<ob> f10845b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13776a((a) obj);
    }

    public oe(Provider<ob> provider) {
        if (f10844a || provider != null) {
            this.f10845b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13775a(Provider<ob> provider) {
        return new oe(provider);
    }

    public void m13776a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10845b;
    }
}
