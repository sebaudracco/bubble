package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class mb implements MembersInjector<lz> {
    static final /* synthetic */ boolean f10740a = (!mb.class.desiredAssertionStatus());
    private final Provider<qg> f10741b;
    private final Provider<mc> f10742c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13675a((lz) obj);
    }

    public mb(Provider<qg> provider, Provider<mc> provider2) {
        if (f10740a || provider != null) {
            this.f10741b = provider;
            if (f10740a || provider2 != null) {
                this.f10742c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<lz> m13674a(Provider<qg> provider, Provider<mc> provider2) {
        return new mb(provider, provider2);
    }

    public void m13675a(lz lzVar) {
        if (lzVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        lzVar.a = (qg) this.f10741b.get();
        lzVar.b = (mc) this.f10742c.get();
    }
}
