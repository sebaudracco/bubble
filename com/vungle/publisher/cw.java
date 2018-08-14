package com.vungle.publisher;

import com.vungle.publisher.ct.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cw implements MembersInjector<a> {
    static final /* synthetic */ boolean f9897a = (!cw.class.desiredAssertionStatus());
    private final Provider<ci> f9898b;
    private final Provider<ct> f9899c;

    public /* synthetic */ void injectMembers(Object obj) {
        m12984a((a) obj);
    }

    public cw(Provider<ci> provider, Provider<ct> provider2) {
        if (f9897a || provider != null) {
            this.f9898b = provider;
            if (f9897a || provider2 != null) {
                this.f9899c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m12983a(Provider<ci> provider, Provider<ct> provider2) {
        return new cw(provider, provider2);
    }

    public void m12984a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f9898b.get();
        aVar.a = this.f9899c;
    }
}
