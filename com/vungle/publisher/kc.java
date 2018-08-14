package com.vungle.publisher;

import com.vungle.publisher.jy.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kc implements MembersInjector<jy> {
    static final /* synthetic */ boolean f10609a = (!kc.class.desiredAssertionStatus());
    private final Provider<ci> f10610b;
    private final Provider<a> f10611c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13563a((jy) obj);
    }

    public kc(Provider<ci> provider, Provider<a> provider2) {
        if (f10609a || provider != null) {
            this.f10610b = provider;
            if (f10609a || provider2 != null) {
                this.f10611c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<jy> m13562a(Provider<ci> provider, Provider<a> provider2) {
        return new kc(provider, provider2);
    }

    public void m13563a(jy jyVar) {
        if (jyVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        jyVar.v = (ci) this.f10610b.get();
        jyVar.d = (a) this.f10611c.get();
    }
}
