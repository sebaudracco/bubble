package com.vungle.publisher;

import com.vungle.publisher.ki.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kq implements MembersInjector<a> {
    static final /* synthetic */ boolean f10637a = (!kq.class.desiredAssertionStatus());
    private final Provider<ci> f10638b;
    private final Provider<co.a> f10639c;
    private final Provider<ct.a> f10640d;
    private final Provider<jn.a> f10641e;
    private final Provider<kd.a> f10642f;
    private final Provider<ki> f10643g;
    private final Provider<ds.a> f10644h;

    public /* synthetic */ void injectMembers(Object obj) {
        m13585a((a) obj);
    }

    public kq(Provider<ci> provider, Provider<co.a> provider2, Provider<ct.a> provider3, Provider<jn.a> provider4, Provider<kd.a> provider5, Provider<ki> provider6, Provider<ds.a> provider7) {
        if (f10637a || provider != null) {
            this.f10638b = provider;
            if (f10637a || provider2 != null) {
                this.f10639c = provider2;
                if (f10637a || provider3 != null) {
                    this.f10640d = provider3;
                    if (f10637a || provider4 != null) {
                        this.f10641e = provider4;
                        if (f10637a || provider5 != null) {
                            this.f10642f = provider5;
                            if (f10637a || provider6 != null) {
                                this.f10643g = provider6;
                                if (f10637a || provider7 != null) {
                                    this.f10644h = provider7;
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

    public static MembersInjector<a> m13584a(Provider<ci> provider, Provider<co.a> provider2, Provider<ct.a> provider3, Provider<jn.a> provider4, Provider<kd.a> provider5, Provider<ki> provider6, Provider<ds.a> provider7) {
        return new kq(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public void m13585a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10638b.get();
        aVar.a = (co.a) this.f10639c.get();
        aVar.b = (ct.a) this.f10640d.get();
        aVar.c = (jn.a) this.f10641e.get();
        aVar.e = (kd.a) this.f10642f.get();
        aVar.f = this.f10643g;
        aVar.g = (ds.a) this.f10644h.get();
    }
}
