package com.vungle.publisher;

import com.vungle.publisher.ks.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kw implements MembersInjector<ks> {
    static final /* synthetic */ boolean f10656a = (!kw.class.desiredAssertionStatus());
    private final Provider<a> f10657b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13595a((ks) obj);
    }

    public kw(Provider<a> provider) {
        if (f10656a || provider != null) {
            this.f10657b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ks> m13594a(Provider<a> provider) {
        return new kw(provider);
    }

    public void m13595a(ks ksVar) {
        if (ksVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ksVar.a = (a) this.f10657b.get();
    }
}
