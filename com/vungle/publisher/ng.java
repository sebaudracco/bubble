package com.vungle.publisher;

import com.vungle.publisher.my.b;
import com.vungle.publisher.my.b.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ng implements MembersInjector<a> {
    static final /* synthetic */ boolean f10799a = (!ng.class.desiredAssertionStatus());
    private final Provider<b> f10800b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13744a((a) obj);
    }

    public ng(Provider<b> provider) {
        if (f10799a || provider != null) {
            this.f10800b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13743a(Provider<b> provider) {
        return new ng(provider);
    }

    public void m13744a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (b) this.f10800b.get();
    }
}
