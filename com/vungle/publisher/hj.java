package com.vungle.publisher;

import com.vungle.publisher.gk.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hj implements MembersInjector<gk> {
    static final /* synthetic */ boolean f10269a = (!hj.class.desiredAssertionStatus());
    private final Provider<ci> f10270b;
    private final Provider<a> f10271c;
    private final Provider<li.a> f10272d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13302a((gk) obj);
    }

    public hj(Provider<ci> provider, Provider<a> provider2, Provider<li.a> provider3) {
        if (f10269a || provider != null) {
            this.f10270b = provider;
            if (f10269a || provider2 != null) {
                this.f10271c = provider2;
                if (f10269a || provider3 != null) {
                    this.f10272d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<gk> m13301a(Provider<ci> provider, Provider<a> provider2, Provider<li.a> provider3) {
        return new hj(provider, provider2, provider3);
    }

    public void m13302a(gk gkVar) {
        if (gkVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gkVar.v = (ci) this.f10270b.get();
        gkVar.x = (a) this.f10271c.get();
        gkVar.y = (li.a) this.f10272d.get();
    }
}
