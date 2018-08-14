package com.vungle.publisher;

import com.vungle.publisher.kd.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kg implements MembersInjector<a> {
    static final /* synthetic */ boolean f10616a = (!kg.class.desiredAssertionStatus());
    private final Provider<ci> f10617b;
    private final Provider<kd> f10618c;
    private final Provider<kj.a> f10619d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13569a((a) obj);
    }

    public kg(Provider<ci> provider, Provider<kd> provider2, Provider<kj.a> provider3) {
        if (f10616a || provider != null) {
            this.f10617b = provider;
            if (f10616a || provider2 != null) {
                this.f10618c = provider2;
                if (f10616a || provider3 != null) {
                    this.f10619d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13568a(Provider<ci> provider, Provider<kd> provider2, Provider<kj.a> provider3) {
        return new kg(provider, provider2, provider3);
    }

    public void m13569a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10617b.get();
        aVar.a = this.f10618c;
        aVar.b = (kj.a) this.f10619d.get();
    }
}
