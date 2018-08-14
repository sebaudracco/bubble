package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class tg implements MembersInjector<te> {
    static final /* synthetic */ boolean f11085a = (!tg.class.desiredAssertionStatus());
    private final Provider<uj> f11086b;
    private final Provider<bw> f11087c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13931a((te) obj);
    }

    public tg(Provider<uj> provider, Provider<bw> provider2) {
        if (f11085a || provider != null) {
            this.f11086b = provider;
            if (f11085a || provider2 != null) {
                this.f11087c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<te> m13930a(Provider<uj> provider, Provider<bw> provider2) {
        return new tg(provider, provider2);
    }

    public void m13931a(te teVar) {
        if (teVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        teVar.a = (uj) this.f11086b.get();
        teVar.b = (bw) this.f11087c.get();
    }
}
