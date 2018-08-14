package com.vungle.publisher;

import com.vungle.publisher.df.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class di implements MembersInjector<a> {
    static final /* synthetic */ boolean f9940a = (!di.class.desiredAssertionStatus());
    private final Provider<ci> f9941b;
    private final Provider<df> f9942c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13049a((a) obj);
    }

    public di(Provider<ci> provider, Provider<df> provider2) {
        if (f9940a || provider != null) {
            this.f9941b = provider;
            if (f9940a || provider2 != null) {
                this.f9942c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13048a(Provider<ci> provider, Provider<df> provider2) {
        return new di(provider, provider2);
    }

    public void m13049a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f9941b.get();
        aVar.a = this.f9942c;
    }
}
