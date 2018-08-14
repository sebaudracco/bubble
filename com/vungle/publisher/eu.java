package com.vungle.publisher;

import com.vungle.publisher.er.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class eu implements MembersInjector<a> {
    static final /* synthetic */ boolean f10081a = (!eu.class.desiredAssertionStatus());
    private final Provider<er> f10082b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13181a((a) obj);
    }

    public eu(Provider<er> provider) {
        if (f10081a || provider != null) {
            this.f10082b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13180a(Provider<er> provider) {
        return new eu(provider);
    }

    public void m13181a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (er) this.f10082b.get();
    }
}
