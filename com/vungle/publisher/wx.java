package com.vungle.publisher;

import com.vungle.publisher.wv.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wx implements MembersInjector<a> {
    static final /* synthetic */ boolean f11280a = (!wx.class.desiredAssertionStatus());
    private final Provider<wa.a> f11281b;

    public /* synthetic */ void injectMembers(Object obj) {
        m14116a((a) obj);
    }

    public wx(Provider<wa.a> provider) {
        if (f11280a || provider != null) {
            this.f11281b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m14115a(Provider<wa.a> provider) {
        return new wx(provider);
    }

    public void m14116a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (wa.a) this.f11281b.get();
    }
}
