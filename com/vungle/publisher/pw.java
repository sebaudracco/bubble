package com.vungle.publisher;

import com.vungle.publisher.pj.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class pw implements MembersInjector<a> {
    static final /* synthetic */ boolean f10923a = (!pw.class.desiredAssertionStatus());
    private final Provider<pj> f10924b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13825a((a) obj);
    }

    public pw(Provider<pj> provider) {
        if (f10923a || provider != null) {
            this.f10924b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13824a(Provider<pj> provider) {
        return new pw(provider);
    }

    public void m13825a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10924b;
    }
}
