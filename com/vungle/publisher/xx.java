package com.vungle.publisher;

import com.vungle.publisher.cz.b;
import com.vungle.publisher.el.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class xx implements MembersInjector<xq> {
    static final /* synthetic */ boolean f11321a = (!xx.class.desiredAssertionStatus());
    private final Provider<b> f11322b;
    private final Provider<xy> f11323c;
    private final Provider<eb.b> f11324d;
    private final Provider<a> f11325e;

    public /* synthetic */ void injectMembers(Object obj) {
        m14139a((xq) obj);
    }

    public xx(Provider<b> provider, Provider<xy> provider2, Provider<eb.b> provider3, Provider<a> provider4) {
        if (f11321a || provider != null) {
            this.f11322b = provider;
            if (f11321a || provider2 != null) {
                this.f11323c = provider2;
                if (f11321a || provider3 != null) {
                    this.f11324d = provider3;
                    if (f11321a || provider4 != null) {
                        this.f11325e = provider4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<xq> m14138a(Provider<b> provider, Provider<xy> provider2, Provider<eb.b> provider3, Provider<a> provider4) {
        return new xx(provider, provider2, provider3, provider4);
    }

    public void m14139a(xq xqVar) {
        if (xqVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        xqVar.a = (b) this.f11322b.get();
        xqVar.b = (xy) this.f11323c.get();
        xqVar.c = (eb.b) this.f11324d.get();
        xqVar.d = (a) this.f11325e.get();
    }
}
