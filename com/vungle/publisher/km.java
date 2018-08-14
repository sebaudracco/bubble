package com.vungle.publisher;

import com.vungle.publisher.kj.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class km implements MembersInjector<a> {
    static final /* synthetic */ boolean f10627a = (!km.class.desiredAssertionStatus());
    private final Provider<ci> f10628b;
    private final Provider<kj> f10629c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13577a((a) obj);
    }

    public km(Provider<ci> provider, Provider<kj> provider2) {
        if (f10627a || provider != null) {
            this.f10628b = provider;
            if (f10627a || provider2 != null) {
                this.f10629c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13576a(Provider<ci> provider, Provider<kj> provider2) {
        return new km(provider, provider2);
    }

    public void m13577a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10628b.get();
        aVar.a = this.f10629c;
    }
}
