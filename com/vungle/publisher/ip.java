package com.vungle.publisher;

import com.vungle.publisher.im.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ip implements MembersInjector<a> {
    static final /* synthetic */ boolean f10476a = (!ip.class.desiredAssertionStatus());
    private final Provider<im> f10477b;
    private final Provider<hs.a> f10478c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13434a((a) obj);
    }

    public ip(Provider<im> provider, Provider<hs.a> provider2) {
        if (f10476a || provider != null) {
            this.f10477b = provider;
            if (f10476a || provider2 != null) {
                this.f10478c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13433a(Provider<im> provider, Provider<hs.a> provider2) {
        return new ip(provider, provider2);
    }

    public void m13434a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10477b;
        aVar.b = (hs.a) this.f10478c.get();
    }
}
