package com.vungle.publisher;

import com.vungle.publisher.hs.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hw implements MembersInjector<hs> {
    static final /* synthetic */ boolean f10306a = (!hw.class.desiredAssertionStatus());
    private final Provider<ci> f10307b;
    private final Provider<a> f10308c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13332a((hs) obj);
    }

    public hw(Provider<ci> provider, Provider<a> provider2) {
        if (f10306a || provider != null) {
            this.f10307b = provider;
            if (f10306a || provider2 != null) {
                this.f10308c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<hs> m13331a(Provider<ci> provider, Provider<a> provider2) {
        return new hw(provider, provider2);
    }

    public void m13332a(hs hsVar) {
        if (hsVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        hsVar.v = (ci) this.f10307b.get();
        hsVar.d = (a) this.f10308c.get();
    }
}
