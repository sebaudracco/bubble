package com.vungle.publisher;

import com.vungle.publisher.bd.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bf implements MembersInjector<a> {
    static final /* synthetic */ boolean f9785a = (!bf.class.desiredAssertionStatus());
    private final Provider<lm> f9786b;

    public /* synthetic */ void injectMembers(Object obj) {
        m12894a((a) obj);
    }

    public bf(Provider<lm> provider) {
        if (f9785a || provider != null) {
            this.f9786b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m12893a(Provider<lm> provider) {
        return new bf(provider);
    }

    public void m12894a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (lm) this.f9786b.get();
    }
}
