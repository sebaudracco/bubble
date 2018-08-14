package com.vungle.publisher;

import com.vungle.publisher.gw.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gz implements MembersInjector<a> {
    static final /* synthetic */ boolean f10217a = (!gz.class.desiredAssertionStatus());
    private final Provider<ci> f10218b;
    private final Provider<gw> f10219c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13281a((a) obj);
    }

    public gz(Provider<ci> provider, Provider<gw> provider2) {
        if (f10217a || provider != null) {
            this.f10218b = provider;
            if (f10217a || provider2 != null) {
                this.f10219c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13280a(Provider<ci> provider, Provider<gw> provider2) {
        return new gz(provider, provider2);
    }

    public void m13281a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10218b.get();
        aVar.a = this.f10219c;
    }
}
