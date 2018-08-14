package com.vungle.publisher;

import com.vungle.publisher.el.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fx implements MembersInjector<a> {
    static final /* synthetic */ boolean f10150a = (!fx.class.desiredAssertionStatus());
    private final Provider<ci> f10151b;
    private final Provider<qg> f10152c;
    private final Provider<zl> f10153d;
    private final Provider<ct.a> f10154e;
    private final Provider<String> f10155f;
    private final Provider<em.a> f10156g;
    private final Provider<el> f10157h;
    private final Provider<ek.a> f10158i;
    private final Provider<er.a> f10159j;
    private final Provider<dw.a> f10160k;
    private final Provider<fq.a> f10161l;

    public /* synthetic */ void injectMembers(Object obj) {
        m13229a((a) obj);
    }

    public fx(Provider<ci> provider, Provider<qg> provider2, Provider<zl> provider3, Provider<ct.a> provider4, Provider<String> provider5, Provider<em.a> provider6, Provider<el> provider7, Provider<ek.a> provider8, Provider<er.a> provider9, Provider<dw.a> provider10, Provider<fq.a> provider11) {
        if (f10150a || provider != null) {
            this.f10151b = provider;
            if (f10150a || provider2 != null) {
                this.f10152c = provider2;
                if (f10150a || provider3 != null) {
                    this.f10153d = provider3;
                    if (f10150a || provider4 != null) {
                        this.f10154e = provider4;
                        if (f10150a || provider5 != null) {
                            this.f10155f = provider5;
                            if (f10150a || provider6 != null) {
                                this.f10156g = provider6;
                                if (f10150a || provider7 != null) {
                                    this.f10157h = provider7;
                                    if (f10150a || provider8 != null) {
                                        this.f10158i = provider8;
                                        if (f10150a || provider9 != null) {
                                            this.f10159j = provider9;
                                            if (f10150a || provider10 != null) {
                                                this.f10160k = provider10;
                                                if (f10150a || provider11 != null) {
                                                    this.f10161l = provider11;
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
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13228a(Provider<ci> provider, Provider<qg> provider2, Provider<zl> provider3, Provider<ct.a> provider4, Provider<String> provider5, Provider<em.a> provider6, Provider<el> provider7, Provider<ek.a> provider8, Provider<er.a> provider9, Provider<dw.a> provider10, Provider<fq.a> provider11) {
        return new fx(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public void m13229a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10151b.get();
        aVar.a = (qg) this.f10152c.get();
        aVar.b = (zl) this.f10153d.get();
        aVar.c = (ct.a) this.f10154e.get();
        aVar.e = this.f10155f;
        aVar.f = (em.a) this.f10156g.get();
        aVar.g = this.f10157h;
        aVar.h = (ek.a) this.f10158i.get();
        aVar.i = (er.a) this.f10159j.get();
        aVar.j = (dw.a) this.f10160k.get();
        aVar.k = (fq.a) this.f10161l.get();
    }
}
