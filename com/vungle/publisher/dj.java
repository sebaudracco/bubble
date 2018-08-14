package com.vungle.publisher;

import com.vungle.publisher.df.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dj implements MembersInjector<df> {
    static final /* synthetic */ boolean f9943a = (!dj.class.desiredAssertionStatus());
    private final Provider<ci> f9944b;
    private final Provider<a> f9945c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13051a((df) obj);
    }

    public dj(Provider<ci> provider, Provider<a> provider2) {
        if (f9943a || provider != null) {
            this.f9944b = provider;
            if (f9943a || provider2 != null) {
                this.f9945c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<df> m13050a(Provider<ci> provider, Provider<a> provider2) {
        return new dj(provider, provider2);
    }

    public void m13051a(df dfVar) {
        if (dfVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        dfVar.v = (ci) this.f9944b.get();
        dfVar.d = (a) this.f9945c.get();
    }
}
