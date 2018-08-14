package com.vungle.publisher;

import com.vungle.publisher.eb.c;
import com.vungle.publisher.m.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ef implements MembersInjector<c> {
    static final /* synthetic */ boolean f9989a = (!ef.class.desiredAssertionStatus());
    private final Provider<ci> f9990b;
    private final Provider<a> f9991c;
    private final Provider<ct.a> f9992d;
    private final Provider<zl> f9993e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13099a((c) obj);
    }

    public ef(Provider<ci> provider, Provider<a> provider2, Provider<ct.a> provider3, Provider<zl> provider4) {
        if (f9989a || provider != null) {
            this.f9990b = provider;
            if (f9989a || provider2 != null) {
                this.f9991c = provider2;
                if (f9989a || provider3 != null) {
                    this.f9992d = provider3;
                    if (f9989a || provider4 != null) {
                        this.f9993e = provider4;
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

    public static MembersInjector<c> m13098a(Provider<ci> provider, Provider<a> provider2, Provider<ct.a> provider3, Provider<zl> provider4) {
        return new ef(provider, provider2, provider3, provider4);
    }

    public void m13099a(c cVar) {
        if (cVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        cVar.a = (ci) this.f9990b.get();
        cVar.b = (a) this.f9991c.get();
        cVar.c = (ct.a) this.f9992d.get();
        cVar.d = (zl) this.f9993e.get();
    }
}
