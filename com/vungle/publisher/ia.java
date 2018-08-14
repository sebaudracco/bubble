package com.vungle.publisher;

import com.vungle.publisher.hx.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ia implements MembersInjector<a> {
    static final /* synthetic */ boolean f10315a = (!ia.class.desiredAssertionStatus());
    private final Provider<ci> f10316b;
    private final Provider<hx> f10317c;
    private final Provider<id.a> f10318d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13340a((a) obj);
    }

    public ia(Provider<ci> provider, Provider<hx> provider2, Provider<id.a> provider3) {
        if (f10315a || provider != null) {
            this.f10316b = provider;
            if (f10315a || provider2 != null) {
                this.f10317c = provider2;
                if (f10315a || provider3 != null) {
                    this.f10318d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13339a(Provider<ci> provider, Provider<hx> provider2, Provider<id.a> provider3) {
        return new ia(provider, provider2, provider3);
    }

    public void m13340a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10316b.get();
        aVar.a = this.f10317c;
        aVar.b = (id.a) this.f10318d.get();
    }
}
