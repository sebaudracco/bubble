package com.vungle.publisher;

import com.vungle.publisher.cn.b;
import com.vungle.publisher.el.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class de implements MembersInjector<b> {
    static final /* synthetic */ boolean f9931a = (!de.class.desiredAssertionStatus());
    private final Provider<a> f9932b;
    private final Provider<hr.a> f9933c;
    private final Provider<jn.a> f9934d;
    private final Provider<gk.a> f9935e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13043a((b) obj);
    }

    public de(Provider<a> provider, Provider<hr.a> provider2, Provider<jn.a> provider3, Provider<gk.a> provider4) {
        if (f9931a || provider != null) {
            this.f9932b = provider;
            if (f9931a || provider2 != null) {
                this.f9933c = provider2;
                if (f9931a || provider3 != null) {
                    this.f9934d = provider3;
                    if (f9931a || provider4 != null) {
                        this.f9935e = provider4;
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

    public static MembersInjector<b> m13042a(Provider<a> provider, Provider<hr.a> provider2, Provider<jn.a> provider3, Provider<gk.a> provider4) {
        return new de(provider, provider2, provider3, provider4);
    }

    public void m13043a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (a) this.f9932b.get();
        bVar.b = (hr.a) this.f9933c.get();
        bVar.c = (jn.a) this.f9934d.get();
        bVar.d = (gk.a) this.f9935e.get();
    }
}
