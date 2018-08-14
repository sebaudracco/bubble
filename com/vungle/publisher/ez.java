package com.vungle.publisher;

import com.vungle.publisher.ew.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ez implements MembersInjector<a> {
    static final /* synthetic */ boolean f10093a = (!ez.class.desiredAssertionStatus());
    private final Provider<ci> f10094b;
    private final Provider<ew> f10095c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13189a((a) obj);
    }

    public ez(Provider<ci> provider, Provider<ew> provider2) {
        if (f10093a || provider != null) {
            this.f10094b = provider;
            if (f10093a || provider2 != null) {
                this.f10095c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13188a(Provider<ci> provider, Provider<ew> provider2) {
        return new ez(provider, provider2);
    }

    public void m13189a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10094b.get();
        aVar.a = this.f10095c;
    }
}
