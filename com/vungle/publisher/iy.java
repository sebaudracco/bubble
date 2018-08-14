package com.vungle.publisher;

import com.vungle.publisher.hr.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class iy implements MembersInjector<hq> {
    static final /* synthetic */ boolean f10505a = (!iy.class.desiredAssertionStatus());
    private final Provider<ci> f10506b;
    private final Provider<a> f10507c;
    private final Provider<hq.a> f10508d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13452a((hq) obj);
    }

    public iy(Provider<ci> provider, Provider<a> provider2, Provider<hq.a> provider3) {
        if (f10505a || provider != null) {
            this.f10506b = provider;
            if (f10505a || provider2 != null) {
                this.f10507c = provider2;
                if (f10505a || provider3 != null) {
                    this.f10508d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<hq> m13451a(Provider<ci> provider, Provider<a> provider2, Provider<hq.a> provider3) {
        return new iy(provider, provider2, provider3);
    }

    public void m13452a(hq hqVar) {
        if (hqVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        hqVar.v = (ci) this.f10506b.get();
        hqVar.a = (a) this.f10507c.get();
        hqVar.b = (hq.a) this.f10508d.get();
    }
}
