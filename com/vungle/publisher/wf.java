package com.vungle.publisher;

import com.vungle.publisher.wd.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wf implements MembersInjector<a> {
    static final /* synthetic */ boolean f11215a = (!wf.class.desiredAssertionStatus());
    private final Provider<C4238m.a> f11216b;

    public /* synthetic */ void injectMembers(Object obj) {
        m14048a((a) obj);
    }

    public wf(Provider<C4238m.a> provider) {
        if (f11215a || provider != null) {
            this.f11216b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m14047a(Provider<C4238m.a> provider) {
        return new wf(provider);
    }

    public void m14048a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (C4238m.a) this.f11216b.get();
    }
}
