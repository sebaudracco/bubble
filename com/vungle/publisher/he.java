package com.vungle.publisher;

import com.vungle.publisher.gv.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class he implements MembersInjector<gv> {
    static final /* synthetic */ boolean f10253a = (!he.class.desiredAssertionStatus());
    private final Provider<ci> f10254b;
    private final Provider<zl> f10255c;
    private final Provider<a> f10256d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13293a((gv) obj);
    }

    public he(Provider<ci> provider, Provider<zl> provider2, Provider<a> provider3) {
        if (f10253a || provider != null) {
            this.f10254b = provider;
            if (f10253a || provider2 != null) {
                this.f10255c = provider2;
                if (f10253a || provider3 != null) {
                    this.f10256d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<gv> m13292a(Provider<ci> provider, Provider<zl> provider2, Provider<a> provider3) {
        return new he(provider, provider2, provider3);
    }

    public void m13293a(gv gvVar) {
        if (gvVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gvVar.v = (ci) this.f10254b.get();
        gvVar.r = (zl) this.f10255c.get();
        gvVar.s = (a) this.f10256d.get();
    }
}
