package com.vungle.publisher.log;

import com.vungle.publisher.env.r;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4235f implements MembersInjector<d> {
    static final /* synthetic */ boolean f10712a = (!C4235f.class.desiredAssertionStatus());
    private final Provider<r> f10713b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13657a((d) obj);
    }

    public C4235f(Provider<r> provider) {
        if (f10712a || provider != null) {
            this.f10713b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<d> m13656a(Provider<r> provider) {
        return new C4235f(provider);
    }

    public void m13657a(d dVar) {
        if (dVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        dVar.b = (r) this.f10713b.get();
    }
}
