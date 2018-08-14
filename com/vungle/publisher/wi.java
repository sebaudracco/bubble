package com.vungle.publisher;

import com.vungle.publisher.wg.a;
import com.vungle.publisher.wp.C4263a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wi implements MembersInjector<a> {
    static final /* synthetic */ boolean f11231a = (!wi.class.desiredAssertionStatus());
    private final Provider<C4238m.a> f11232b;
    private final Provider<C4263a.a> f11233c;
    private final Provider<wv.a> f11234d;

    public /* synthetic */ void injectMembers(Object obj) {
        m14064a((a) obj);
    }

    public wi(Provider<C4238m.a> provider, Provider<C4263a.a> provider2, Provider<wv.a> provider3) {
        if (f11231a || provider != null) {
            this.f11232b = provider;
            if (f11231a || provider2 != null) {
                this.f11233c = provider2;
                if (f11231a || provider3 != null) {
                    this.f11234d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m14063a(Provider<C4238m.a> provider, Provider<C4263a.a> provider2, Provider<wv.a> provider3) {
        return new wi(provider, provider2, provider3);
    }

    public void m14064a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (C4238m.a) this.f11232b.get();
        aVar.c = (C4263a.a) this.f11233c.get();
        aVar.b = (wv.a) this.f11234d.get();
    }
}
