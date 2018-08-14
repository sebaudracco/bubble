package com.vungle.publisher;

import com.vungle.publisher.fq.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ft implements MembersInjector<a> {
    static final /* synthetic */ boolean f10141a = (!ft.class.desiredAssertionStatus());
    private final Provider<fq> f10142b;
    private final Provider<ew.a> f10143c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13221a((a) obj);
    }

    public ft(Provider<fq> provider, Provider<ew.a> provider2) {
        if (f10141a || provider != null) {
            this.f10142b = provider;
            if (f10141a || provider2 != null) {
                this.f10143c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13220a(Provider<fq> provider, Provider<ew.a> provider2) {
        return new ft(provider, provider2);
    }

    public void m13221a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10142b;
        aVar.b = (ew.a) this.f10143c.get();
    }
}
