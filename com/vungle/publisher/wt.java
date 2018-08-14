package com.vungle.publisher;

import com.vungle.publisher.wr.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wt implements MembersInjector<a> {
    static final /* synthetic */ boolean f11261a = (!wt.class.desiredAssertionStatus());
    private final Provider<C4238m.a> f11262b;
    private final Provider<xb.a> f11263c;
    private final Provider<wz.a> f11264d;
    private final Provider<vu.a> f11265e;

    public /* synthetic */ void injectMembers(Object obj) {
        m14094a((a) obj);
    }

    public wt(Provider<C4238m.a> provider, Provider<xb.a> provider2, Provider<wz.a> provider3, Provider<vu.a> provider4) {
        if (f11261a || provider != null) {
            this.f11262b = provider;
            if (f11261a || provider2 != null) {
                this.f11263c = provider2;
                if (f11261a || provider3 != null) {
                    this.f11264d = provider3;
                    if (f11261a || provider4 != null) {
                        this.f11265e = provider4;
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

    public static MembersInjector<a> m14093a(Provider<C4238m.a> provider, Provider<xb.a> provider2, Provider<wz.a> provider3, Provider<vu.a> provider4) {
        return new wt(provider, provider2, provider3, provider4);
    }

    public void m14094a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (C4238m.a) this.f11262b.get();
        aVar.b = (xb.a) this.f11263c.get();
        aVar.c = (wz.a) this.f11264d.get();
        aVar.d = (vu.a) this.f11265e.get();
    }
}
