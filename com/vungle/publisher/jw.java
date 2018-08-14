package com.vungle.publisher;

import com.vungle.publisher.jt.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jw implements MembersInjector<a> {
    static final /* synthetic */ boolean f10592a = (!jw.class.desiredAssertionStatus());
    private final Provider<jt> f10593b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13551a((a) obj);
    }

    public jw(Provider<jt> provider) {
        if (f10592a || provider != null) {
            this.f10593b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13550a(Provider<jt> provider) {
        return new jw(provider);
    }

    public void m13551a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (jt) this.f10593b.get();
    }
}
