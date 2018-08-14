package com.vungle.publisher;

import com.vungle.publisher.hr.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class iu implements MembersInjector<hr> {
    static final /* synthetic */ boolean f10493a = (!iu.class.desiredAssertionStatus());
    private final Provider<ci> f10494b;
    private final Provider<qg> f10495c;
    private final Provider<a> f10496d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13444a((hr) obj);
    }

    public iu(Provider<ci> provider, Provider<qg> provider2, Provider<a> provider3) {
        if (f10493a || provider != null) {
            this.f10494b = provider;
            if (f10493a || provider2 != null) {
                this.f10495c = provider2;
                if (f10493a || provider3 != null) {
                    this.f10496d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<hr> m13443a(Provider<ci> provider, Provider<qg> provider2, Provider<a> provider3) {
        return new iu(provider, provider2, provider3);
    }

    public void m13444a(hr hrVar) {
        if (hrVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        hrVar.v = (ci) this.f10494b.get();
        hrVar.F = (qg) this.f10495c.get();
        hrVar.w = (a) this.f10496d.get();
    }
}
