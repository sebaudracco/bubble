package com.vungle.publisher;

import com.vungle.publisher.eb.c;
import com.vungle.publisher.hk.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gp implements MembersInjector<gl> {
    static final /* synthetic */ boolean f10196a = (!gp.class.desiredAssertionStatus());
    private final Provider<ci> f10197b;
    private final Provider<c> f10198c;
    private final Provider<zl> f10199d;
    private final Provider<a> f10200e;
    private final Provider<ct.a> f10201f;

    public /* synthetic */ void injectMembers(Object obj) {
        m13267a((gl) obj);
    }

    public gp(Provider<ci> provider, Provider<c> provider2, Provider<zl> provider3, Provider<a> provider4, Provider<ct.a> provider5) {
        if (f10196a || provider != null) {
            this.f10197b = provider;
            if (f10196a || provider2 != null) {
                this.f10198c = provider2;
                if (f10196a || provider3 != null) {
                    this.f10199d = provider3;
                    if (f10196a || provider4 != null) {
                        this.f10200e = provider4;
                        if (f10196a || provider5 != null) {
                            this.f10201f = provider5;
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
        throw new AssertionError();
    }

    public static MembersInjector<gl> m13266a(Provider<ci> provider, Provider<c> provider2, Provider<zl> provider3, Provider<a> provider4, Provider<ct.a> provider5) {
        return new gp(provider, provider2, provider3, provider4, provider5);
    }

    public void m13267a(gl glVar) {
        if (glVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        glVar.a = (ci) this.f10197b.get();
        glVar.b = (c) this.f10198c.get();
        glVar.c = (zl) this.f10199d.get();
        glVar.d = (a) this.f10200e.get();
        glVar.e = (ct.a) this.f10201f.get();
    }
}
