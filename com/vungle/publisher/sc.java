package com.vungle.publisher;

import com.vungle.publisher.rz.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class sc implements MembersInjector<a> {
    static final /* synthetic */ boolean f11048a = (!sc.class.desiredAssertionStatus());
    private final Provider<rz> f11049b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13904a((a) obj);
    }

    public sc(Provider<rz> provider) {
        if (f11048a || provider != null) {
            this.f11049b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13903a(Provider<rz> provider) {
        return new sc(provider);
    }

    public void m13904a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f11049b;
    }
}
