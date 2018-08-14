package com.vungle.publisher;

import com.vungle.publisher.gk.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hi implements MembersInjector<a> {
    static final /* synthetic */ boolean f10262a = (!hi.class.desiredAssertionStatus());
    private final Provider<ci> f10263b;
    private final Provider<qg> f10264c;
    private final Provider<zl> f10265d;
    private final Provider<ct.a> f10266e;
    private final Provider<gk> f10267f;
    private final Provider<gl.a> f10268g;

    public /* synthetic */ void injectMembers(Object obj) {
        m13300a((a) obj);
    }

    public hi(Provider<ci> provider, Provider<qg> provider2, Provider<zl> provider3, Provider<ct.a> provider4, Provider<gk> provider5, Provider<gl.a> provider6) {
        if (f10262a || provider != null) {
            this.f10263b = provider;
            if (f10262a || provider2 != null) {
                this.f10264c = provider2;
                if (f10262a || provider3 != null) {
                    this.f10265d = provider3;
                    if (f10262a || provider4 != null) {
                        this.f10266e = provider4;
                        if (f10262a || provider5 != null) {
                            this.f10267f = provider5;
                            if (f10262a || provider6 != null) {
                                this.f10268g = provider6;
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

    public static MembersInjector<a> m13299a(Provider<ci> provider, Provider<qg> provider2, Provider<zl> provider3, Provider<ct.a> provider4, Provider<gk> provider5, Provider<gl.a> provider6) {
        return new hi(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public void m13300a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10263b.get();
        aVar.a = (qg) this.f10264c.get();
        aVar.b = (zl) this.f10265d.get();
        aVar.c = (ct.a) this.f10266e.get();
        aVar.e = this.f10267f;
        aVar.f = (gl.a) this.f10268g.get();
    }
}
