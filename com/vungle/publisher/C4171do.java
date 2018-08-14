package com.vungle.publisher;

import com.vungle.publisher.jn.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4171do implements MembersInjector<dk> {
    static final /* synthetic */ boolean f9956a = (!C4171do.class.desiredAssertionStatus());
    private final Provider<ci> f9957b;
    private final Provider<a> f9958c;
    private final Provider<dk.a> f9959d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13059a((dk) obj);
    }

    public C4171do(Provider<ci> provider, Provider<a> provider2, Provider<dk.a> provider3) {
        if (f9956a || provider != null) {
            this.f9957b = provider;
            if (f9956a || provider2 != null) {
                this.f9958c = provider2;
                if (f9956a || provider3 != null) {
                    this.f9959d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<dk> m13058a(Provider<ci> provider, Provider<a> provider2, Provider<dk.a> provider3) {
        return new C4171do(provider, provider2, provider3);
    }

    public void m13059a(dk dkVar) {
        if (dkVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        dkVar.v = (ci) this.f9957b.get();
        dkVar.e = (a) this.f9958c.get();
        dkVar.f = (dk.a) this.f9959d.get();
    }
}
