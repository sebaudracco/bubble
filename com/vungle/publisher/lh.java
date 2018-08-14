package com.vungle.publisher;

import com.vungle.publisher.lb.a;
import com.vungle.publisher.lf.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class lh implements MembersInjector<b> {
    static final /* synthetic */ boolean f10692a = (!lh.class.desiredAssertionStatus());
    private final Provider<a> f10693b;
    private final Provider<li.a> f10694c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13620a((b) obj);
    }

    public lh(Provider<a> provider, Provider<li.a> provider2) {
        if (f10692a || provider != null) {
            this.f10693b = provider;
            if (f10692a || provider2 != null) {
                this.f10694c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<b> m13619a(Provider<a> provider, Provider<li.a> provider2) {
        return new lh(provider, provider2);
    }

    public void m13620a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (a) this.f10693b.get();
        bVar.b = (li.a) this.f10694c.get();
    }
}
