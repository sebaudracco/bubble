package com.vungle.publisher.log;

import com.vungle.publisher.env.n;
import com.vungle.publisher.env.r;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4233c implements MembersInjector<a> {
    static final /* synthetic */ boolean f10707a = (!C4233c.class.desiredAssertionStatus());
    private final Provider<r> f10708b;
    private final Provider<n> f10709c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13653a((a) obj);
    }

    public C4233c(Provider<r> provider, Provider<n> provider2) {
        if (f10707a || provider != null) {
            this.f10708b = provider;
            if (f10707a || provider2 != null) {
                this.f10709c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13652a(Provider<r> provider, Provider<n> provider2) {
        return new C4233c(provider, provider2);
    }

    public void m13653a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (r) this.f10708b.get();
        aVar.b = (n) this.f10709c.get();
    }
}
