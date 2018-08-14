package com.vungle.publisher;

import com.vungle.publisher.wm.a;
import com.vungle.publisher.wp.C4263a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wo implements MembersInjector<a> {
    static final /* synthetic */ boolean f11243a = (!wo.class.desiredAssertionStatus());
    private final Provider<C4238m.a> f11244b;
    private final Provider<C4263a.a> f11245c;
    private final Provider<wv.a> f11246d;

    public /* synthetic */ void injectMembers(Object obj) {
        m14075a((a) obj);
    }

    public wo(Provider<C4238m.a> provider, Provider<C4263a.a> provider2, Provider<wv.a> provider3) {
        if (f11243a || provider != null) {
            this.f11244b = provider;
            if (f11243a || provider2 != null) {
                this.f11245c = provider2;
                if (f11243a || provider3 != null) {
                    this.f11246d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m14074a(Provider<C4238m.a> provider, Provider<C4263a.a> provider2, Provider<wv.a> provider3) {
        return new wo(provider, provider2, provider3);
    }

    public void m14075a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (C4238m.a) this.f11244b.get();
        aVar.c = (C4263a.a) this.f11245c.get();
        aVar.b = (wv.a) this.f11246d.get();
    }
}
