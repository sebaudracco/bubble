package com.vungle.publisher;

import com.vungle.publisher.hr.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class it implements MembersInjector<a> {
    static final /* synthetic */ boolean f10485a = (!it.class.desiredAssertionStatus());
    private final Provider<ci> f10486b;
    private final Provider<qg> f10487c;
    private final Provider<zl> f10488d;
    private final Provider<ct.a> f10489e;
    private final Provider<hr> f10490f;
    private final Provider<hq.a> f10491g;
    private final Provider<im.a> f10492h;

    public /* synthetic */ void injectMembers(Object obj) {
        m13442a((a) obj);
    }

    public it(Provider<ci> provider, Provider<qg> provider2, Provider<zl> provider3, Provider<ct.a> provider4, Provider<hr> provider5, Provider<hq.a> provider6, Provider<im.a> provider7) {
        if (f10485a || provider != null) {
            this.f10486b = provider;
            if (f10485a || provider2 != null) {
                this.f10487c = provider2;
                if (f10485a || provider3 != null) {
                    this.f10488d = provider3;
                    if (f10485a || provider4 != null) {
                        this.f10489e = provider4;
                        if (f10485a || provider5 != null) {
                            this.f10490f = provider5;
                            if (f10485a || provider6 != null) {
                                this.f10491g = provider6;
                                if (f10485a || provider7 != null) {
                                    this.f10492h = provider7;
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

    public static MembersInjector<a> m13441a(Provider<ci> provider, Provider<qg> provider2, Provider<zl> provider3, Provider<ct.a> provider4, Provider<hr> provider5, Provider<hq.a> provider6, Provider<im.a> provider7) {
        return new it(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public void m13442a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10486b.get();
        aVar.a = (qg) this.f10487c.get();
        aVar.b = (zl) this.f10488d.get();
        aVar.c = (ct.a) this.f10489e.get();
        aVar.e = this.f10490f;
        aVar.f = (hq.a) this.f10491g.get();
        aVar.g = (im.a) this.f10492h.get();
    }
}
