package com.vungle.publisher;

import com.vungle.publisher.em.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ep implements MembersInjector<a> {
    static final /* synthetic */ boolean f10065a = (!ep.class.desiredAssertionStatus());
    private final Provider<ci> f10066b;
    private final Provider<m.a> f10067c;
    private final Provider<ge.a> f10068d;
    private final Provider<em> f10069e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13173a((a) obj);
    }

    public ep(Provider<ci> provider, Provider<m.a> provider2, Provider<ge.a> provider3, Provider<em> provider4) {
        if (f10065a || provider != null) {
            this.f10066b = provider;
            if (f10065a || provider2 != null) {
                this.f10067c = provider2;
                if (f10065a || provider3 != null) {
                    this.f10068d = provider3;
                    if (f10065a || provider4 != null) {
                        this.f10069e = provider4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13172a(Provider<ci> provider, Provider<m.a> provider2, Provider<ge.a> provider3, Provider<em> provider4) {
        return new ep(provider, provider2, provider3, provider4);
    }

    public void m13173a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10066b.get();
        aVar.e = (m.a) this.f10067c.get();
        aVar.a = (ge.a) this.f10068d.get();
        aVar.b = this.f10069e;
    }
}
