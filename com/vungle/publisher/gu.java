package com.vungle.publisher;

import com.vungle.publisher.gq.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gu implements MembersInjector<gq> {
    static final /* synthetic */ boolean f10210a = (!gu.class.desiredAssertionStatus());
    private final Provider<ci> f10211b;
    private final Provider<a> f10212c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13275a((gq) obj);
    }

    public gu(Provider<ci> provider, Provider<a> provider2) {
        if (f10210a || provider != null) {
            this.f10211b = provider;
            if (f10210a || provider2 != null) {
                this.f10212c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<gq> m13274a(Provider<ci> provider, Provider<a> provider2) {
        return new gu(provider, provider2);
    }

    public void m13275a(gq gqVar) {
        if (gqVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gqVar.v = (ci) this.f10211b.get();
        gqVar.e = (a) this.f10212c.get();
    }
}
