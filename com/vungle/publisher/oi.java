package com.vungle.publisher;

import com.vungle.publisher.og.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class oi implements MembersInjector<a> {
    static final /* synthetic */ boolean f10858a = (!oi.class.desiredAssertionStatus());
    private final Provider<zo> f10859b;
    private final Provider<qg> f10860c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13785a((a) obj);
    }

    public oi(Provider<zo> provider, Provider<qg> provider2) {
        if (f10858a || provider != null) {
            this.f10859b = provider;
            if (f10858a || provider2 != null) {
                this.f10860c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13784a(Provider<zo> provider, Provider<qg> provider2) {
        return new oi(provider, provider2);
    }

    public void m13785a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (zo) this.f10859b.get();
        aVar.b = (qg) this.f10860c.get();
    }
}
