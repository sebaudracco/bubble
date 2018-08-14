package com.vungle.publisher;

import com.vungle.publisher.gq.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gt implements MembersInjector<a> {
    static final /* synthetic */ boolean f10206a = (!gt.class.desiredAssertionStatus());
    private final Provider<ci> f10207b;
    private final Provider<gq> f10208c;
    private final Provider<gw.a> f10209d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13273a((a) obj);
    }

    public gt(Provider<ci> provider, Provider<gq> provider2, Provider<gw.a> provider3) {
        if (f10206a || provider != null) {
            this.f10207b = provider;
            if (f10206a || provider2 != null) {
                this.f10208c = provider2;
                if (f10206a || provider3 != null) {
                    this.f10209d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13272a(Provider<ci> provider, Provider<gq> provider2, Provider<gw.a> provider3) {
        return new gt(provider, provider2, provider3);
    }

    public void m13273a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10207b.get();
        aVar.a = this.f10208c;
        aVar.b = (gw.a) this.f10209d.get();
    }
}
