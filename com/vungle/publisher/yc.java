package com.vungle.publisher;

import com.vungle.publisher.th.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class yc implements MembersInjector<xy> {
    static final /* synthetic */ boolean f11329a = (!yc.class.desiredAssertionStatus());
    private final Provider<a> f11330b;
    private final Provider<uu> f11331c;
    private final Provider<uz> f11332d;
    private final Provider<yd> f11333e;

    public /* synthetic */ void injectMembers(Object obj) {
        m14143a((xy) obj);
    }

    public yc(Provider<a> provider, Provider<uu> provider2, Provider<uz> provider3, Provider<yd> provider4) {
        if (f11329a || provider != null) {
            this.f11330b = provider;
            if (f11329a || provider2 != null) {
                this.f11331c = provider2;
                if (f11329a || provider3 != null) {
                    this.f11332d = provider3;
                    if (f11329a || provider4 != null) {
                        this.f11333e = provider4;
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

    public static MembersInjector<xy> m14142a(Provider<a> provider, Provider<uu> provider2, Provider<uz> provider3, Provider<yd> provider4) {
        return new yc(provider, provider2, provider3, provider4);
    }

    public void m14143a(xy xyVar) {
        if (xyVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        xyVar.a = (a) this.f11330b.get();
        xyVar.b = (uu) this.f11331c.get();
        xyVar.c = (uz) this.f11332d.get();
        xyVar.d = (yd) this.f11333e.get();
    }
}
