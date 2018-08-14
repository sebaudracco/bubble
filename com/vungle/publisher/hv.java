package com.vungle.publisher;

import com.vungle.publisher.hs.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hv implements MembersInjector<a> {
    static final /* synthetic */ boolean f10303a = (!hv.class.desiredAssertionStatus());
    private final Provider<ci> f10304b;
    private final Provider<hs> f10305c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13330a((a) obj);
    }

    public hv(Provider<ci> provider, Provider<hs> provider2) {
        if (f10303a || provider != null) {
            this.f10304b = provider;
            if (f10303a || provider2 != null) {
                this.f10305c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13329a(Provider<ci> provider, Provider<hs> provider2) {
        return new hv(provider, provider2);
    }

    public void m13330a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10304b.get();
        aVar.a = this.f10305c;
    }
}
