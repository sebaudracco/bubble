package com.vungle.publisher;

import com.vungle.publisher.fh.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fk implements MembersInjector<a> {
    static final /* synthetic */ boolean f10114a = (!fk.class.desiredAssertionStatus());
    private final Provider<ci> f10115b;
    private final Provider<fh> f10116c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13205a((a) obj);
    }

    public fk(Provider<ci> provider, Provider<fh> provider2) {
        if (f10114a || provider != null) {
            this.f10115b = provider;
            if (f10114a || provider2 != null) {
                this.f10116c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13204a(Provider<ci> provider, Provider<fh> provider2) {
        return new fk(provider, provider2);
    }

    public void m13205a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10115b.get();
        aVar.a = this.f10116c;
    }
}
