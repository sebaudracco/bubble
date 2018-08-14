package com.vungle.publisher;

import com.vungle.publisher.eb.b;
import com.vungle.publisher.eb.c;
import com.vungle.publisher.m.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ed implements MembersInjector<b> {
    static final /* synthetic */ boolean f9979a = (!ed.class.desiredAssertionStatus());
    private final Provider<ci> f9980b;
    private final Provider<a> f9981c;
    private final Provider<ct.a> f9982d;
    private final Provider<cn.b> f9983e;
    private final Provider<jt.a> f9984f;
    private final Provider<er.a> f9985g;
    private final Provider<c> f9986h;

    public /* synthetic */ void injectMembers(Object obj) {
        m13095a((b) obj);
    }

    public ed(Provider<ci> provider, Provider<a> provider2, Provider<ct.a> provider3, Provider<cn.b> provider4, Provider<jt.a> provider5, Provider<er.a> provider6, Provider<c> provider7) {
        if (f9979a || provider != null) {
            this.f9980b = provider;
            if (f9979a || provider2 != null) {
                this.f9981c = provider2;
                if (f9979a || provider3 != null) {
                    this.f9982d = provider3;
                    if (f9979a || provider4 != null) {
                        this.f9983e = provider4;
                        if (f9979a || provider5 != null) {
                            this.f9984f = provider5;
                            if (f9979a || provider6 != null) {
                                this.f9985g = provider6;
                                if (f9979a || provider7 != null) {
                                    this.f9986h = provider7;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<b> m13094a(Provider<ci> provider, Provider<a> provider2, Provider<ct.a> provider3, Provider<cn.b> provider4, Provider<jt.a> provider5, Provider<er.a> provider6, Provider<c> provider7) {
        return new ed(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public void m13095a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (ci) this.f9980b.get();
        bVar.b = (a) this.f9981c.get();
        bVar.c = (ct.a) this.f9982d.get();
        bVar.d = (cn.b) this.f9983e.get();
        bVar.e = (jt.a) this.f9984f.get();
        bVar.f = (er.a) this.f9985g.get();
        bVar.g = (c) this.f9986h.get();
    }
}
