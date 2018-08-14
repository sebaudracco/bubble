package com.vungle.publisher;

import com.vungle.publisher.hk.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hn implements MembersInjector<a> {
    static final /* synthetic */ boolean f10277a = (!hn.class.desiredAssertionStatus());
    private final Provider<ci> f10278b;
    private final Provider<hk> f10279c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13308a((a) obj);
    }

    public hn(Provider<ci> provider, Provider<hk> provider2) {
        if (f10277a || provider != null) {
            this.f10278b = provider;
            if (f10277a || provider2 != null) {
                this.f10279c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13307a(Provider<ci> provider, Provider<hk> provider2) {
        return new hn(provider, provider2);
    }

    public void m13308a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10278b.get();
        aVar.a = this.f10279c;
    }
}
