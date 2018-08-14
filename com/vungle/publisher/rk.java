package com.vungle.publisher;

import com.vungle.publisher.rg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rk implements MembersInjector<a> {
    static final /* synthetic */ boolean f10969a = (!rk.class.desiredAssertionStatus());
    private final Provider<rg> f10970b;
    private final Provider<iz.a> f10971c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13877a((a) obj);
    }

    public rk(Provider<rg> provider, Provider<iz.a> provider2) {
        if (f10969a || provider != null) {
            this.f10970b = provider;
            if (f10969a || provider2 != null) {
                this.f10971c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13876a(Provider<rg> provider, Provider<iz.a> provider2) {
        return new rk(provider, provider2);
    }

    public void m13877a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10970b;
        aVar.b = (iz.a) this.f10971c.get();
    }
}
