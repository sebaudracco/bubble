package com.vungle.publisher;

import com.vungle.publisher.eb.c;
import com.vungle.publisher.hk.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ev implements MembersInjector<er> {
    static final /* synthetic */ boolean f10083a = (!ev.class.desiredAssertionStatus());
    private final Provider<ci> f10084b;
    private final Provider<c> f10085c;
    private final Provider<zl> f10086d;
    private final Provider<a> f10087e;
    private final Provider<ct.a> f10088f;

    public /* synthetic */ void injectMembers(Object obj) {
        m13183a((er) obj);
    }

    public ev(Provider<ci> provider, Provider<c> provider2, Provider<zl> provider3, Provider<a> provider4, Provider<ct.a> provider5) {
        if (f10083a || provider != null) {
            this.f10084b = provider;
            if (f10083a || provider2 != null) {
                this.f10085c = provider2;
                if (f10083a || provider3 != null) {
                    this.f10086d = provider3;
                    if (f10083a || provider4 != null) {
                        this.f10087e = provider4;
                        if (f10083a || provider5 != null) {
                            this.f10088f = provider5;
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

    public static MembersInjector<er> m13182a(Provider<ci> provider, Provider<c> provider2, Provider<zl> provider3, Provider<a> provider4, Provider<ct.a> provider5) {
        return new ev(provider, provider2, provider3, provider4, provider5);
    }

    public void m13183a(er erVar) {
        if (erVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        erVar.a = (ci) this.f10084b.get();
        erVar.b = (c) this.f10085c.get();
        erVar.c = (zl) this.f10086d.get();
        erVar.d = (a) this.f10087e.get();
        erVar.e = (ct.a) this.f10088f.get();
    }
}
