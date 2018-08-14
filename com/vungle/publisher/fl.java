package com.vungle.publisher;

import com.vungle.publisher.fh.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fl implements MembersInjector<fh> {
    static final /* synthetic */ boolean f10117a = (!fl.class.desiredAssertionStatus());
    private final Provider<ci> f10118b;
    private final Provider<a> f10119c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13207a((fh) obj);
    }

    public fl(Provider<ci> provider, Provider<a> provider2) {
        if (f10117a || provider != null) {
            this.f10118b = provider;
            if (f10117a || provider2 != null) {
                this.f10119c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<fh> m13206a(Provider<ci> provider, Provider<a> provider2) {
        return new fl(provider, provider2);
    }

    public void m13207a(fh fhVar) {
        if (fhVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        fhVar.v = (ci) this.f10118b.get();
        fhVar.e = (a) this.f10119c.get();
    }
}
