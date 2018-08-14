package com.vungle.publisher;

import com.vungle.publisher.kj.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kn implements MembersInjector<kj> {
    static final /* synthetic */ boolean f10630a = (!kn.class.desiredAssertionStatus());
    private final Provider<ci> f10631b;
    private final Provider<a> f10632c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13579a((kj) obj);
    }

    public kn(Provider<ci> provider, Provider<a> provider2) {
        if (f10630a || provider != null) {
            this.f10631b = provider;
            if (f10630a || provider2 != null) {
                this.f10632c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<kj> m13578a(Provider<ci> provider, Provider<a> provider2) {
        return new kn(provider, provider2);
    }

    public void m13579a(kj kjVar) {
        if (kjVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        kjVar.v = (ci) this.f10631b.get();
        kjVar.e = (a) this.f10632c.get();
    }
}
