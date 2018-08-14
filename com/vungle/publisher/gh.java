package com.vungle.publisher;

import com.vungle.publisher.ge.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gh implements MembersInjector<a> {
    static final /* synthetic */ boolean f10185a = (!gh.class.desiredAssertionStatus());
    private final Provider<ge> f10186b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13257a((a) obj);
    }

    public gh(Provider<ge> provider) {
        if (f10185a || provider != null) {
            this.f10186b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13256a(Provider<ge> provider) {
        return new gh(provider);
    }

    public void m13257a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10186b;
    }
}
