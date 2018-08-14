package com.vungle.publisher;

import com.vungle.publisher.hk.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ho implements MembersInjector<hk> {
    static final /* synthetic */ boolean f10280a = (!ho.class.desiredAssertionStatus());
    private final Provider<ci> f10281b;
    private final Provider<a> f10282c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13310a((hk) obj);
    }

    public ho(Provider<ci> provider, Provider<a> provider2) {
        if (f10280a || provider != null) {
            this.f10281b = provider;
            if (f10280a || provider2 != null) {
                this.f10282c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<hk> m13309a(Provider<ci> provider, Provider<a> provider2) {
        return new ho(provider, provider2);
    }

    public void m13310a(hk hkVar) {
        if (hkVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        hkVar.v = (ci) this.f10281b.get();
        hkVar.c = (a) this.f10282c.get();
    }
}
