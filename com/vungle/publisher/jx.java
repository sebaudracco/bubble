package com.vungle.publisher;

import com.vungle.publisher.eb.c;
import com.vungle.publisher.hk.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jx implements MembersInjector<jt> {
    static final /* synthetic */ boolean f10594a = (!jx.class.desiredAssertionStatus());
    private final Provider<ci> f10595b;
    private final Provider<c> f10596c;
    private final Provider<zl> f10597d;
    private final Provider<a> f10598e;
    private final Provider<ct.a> f10599f;

    public /* synthetic */ void injectMembers(Object obj) {
        m13553a((jt) obj);
    }

    public jx(Provider<ci> provider, Provider<c> provider2, Provider<zl> provider3, Provider<a> provider4, Provider<ct.a> provider5) {
        if (f10594a || provider != null) {
            this.f10595b = provider;
            if (f10594a || provider2 != null) {
                this.f10596c = provider2;
                if (f10594a || provider3 != null) {
                    this.f10597d = provider3;
                    if (f10594a || provider4 != null) {
                        this.f10598e = provider4;
                        if (f10594a || provider5 != null) {
                            this.f10599f = provider5;
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

    public static MembersInjector<jt> m13552a(Provider<ci> provider, Provider<c> provider2, Provider<zl> provider3, Provider<a> provider4, Provider<ct.a> provider5) {
        return new jx(provider, provider2, provider3, provider4, provider5);
    }

    public void m13553a(jt jtVar) {
        if (jtVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        jtVar.a = (ci) this.f10595b.get();
        jtVar.b = (c) this.f10596c.get();
        jtVar.c = (zl) this.f10597d.get();
        jtVar.d = (a) this.f10598e.get();
        jtVar.e = (ct.a) this.f10599f.get();
    }
}
