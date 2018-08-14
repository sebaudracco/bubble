package com.vungle.publisher.env;

import android.content.SharedPreferences;
import com.vungle.publisher.bw;
import com.vungle.publisher.cf;
import com.vungle.publisher.ct.a;
import com.vungle.publisher.lp;
import com.vungle.publisher.qg;
import com.vungle.publisher.ta;
import com.vungle.publisher.zl;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4188v implements MembersInjector<r> {
    static final /* synthetic */ boolean f10045a = (!C4188v.class.desiredAssertionStatus());
    private final Provider<zl> f10046b;
    private final Provider<cf> f10047c;
    private final Provider<C4181i> f10048d;
    private final Provider<qg> f10049e;
    private final Provider<lp> f10050f;
    private final Provider<ta> f10051g;
    private final Provider<bw> f10052h;
    private final Provider<k> f10053i;
    private final Provider<o> f10054j;
    private final Provider<SharedPreferences> f10055k;
    private final Provider<a> f10056l;
    private final Provider<C4179n> f10057m;
    private final Provider<WrapperFramework> f10058n;
    private final Provider<String> f10059o;

    public /* synthetic */ void injectMembers(Object obj) {
        m13169a((r) obj);
    }

    public C4188v(Provider<zl> provider, Provider<cf> provider2, Provider<C4181i> provider3, Provider<qg> provider4, Provider<lp> provider5, Provider<ta> provider6, Provider<bw> provider7, Provider<k> provider8, Provider<o> provider9, Provider<SharedPreferences> provider10, Provider<a> provider11, Provider<C4179n> provider12, Provider<WrapperFramework> provider13, Provider<String> provider14) {
        if (f10045a || provider != null) {
            this.f10046b = provider;
            if (f10045a || provider2 != null) {
                this.f10047c = provider2;
                if (f10045a || provider3 != null) {
                    this.f10048d = provider3;
                    if (f10045a || provider4 != null) {
                        this.f10049e = provider4;
                        if (f10045a || provider5 != null) {
                            this.f10050f = provider5;
                            if (f10045a || provider6 != null) {
                                this.f10051g = provider6;
                                if (f10045a || provider7 != null) {
                                    this.f10052h = provider7;
                                    if (f10045a || provider8 != null) {
                                        this.f10053i = provider8;
                                        if (f10045a || provider9 != null) {
                                            this.f10054j = provider9;
                                            if (f10045a || provider10 != null) {
                                                this.f10055k = provider10;
                                                if (f10045a || provider11 != null) {
                                                    this.f10056l = provider11;
                                                    if (f10045a || provider12 != null) {
                                                        this.f10057m = provider12;
                                                        if (f10045a || provider13 != null) {
                                                            this.f10058n = provider13;
                                                            if (f10045a || provider14 != null) {
                                                                this.f10059o = provider14;
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
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<r> m13168a(Provider<zl> provider, Provider<cf> provider2, Provider<C4181i> provider3, Provider<qg> provider4, Provider<lp> provider5, Provider<ta> provider6, Provider<bw> provider7, Provider<k> provider8, Provider<o> provider9, Provider<SharedPreferences> provider10, Provider<a> provider11, Provider<C4179n> provider12, Provider<WrapperFramework> provider13, Provider<String> provider14) {
        return new C4188v(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
    }

    public void m13169a(r rVar) {
        if (rVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        rVar.c = (zl) this.f10046b.get();
        rVar.d = (cf) this.f10047c.get();
        rVar.e = (C4181i) this.f10048d.get();
        rVar.f = (qg) this.f10049e.get();
        rVar.g = (lp) this.f10050f.get();
        rVar.h = (ta) this.f10051g.get();
        rVar.i = (bw) this.f10052h.get();
        rVar.j = (k) this.f10053i.get();
        rVar.k = (o) this.f10054j.get();
        rVar.l = (SharedPreferences) this.f10055k.get();
        rVar.m = (a) this.f10056l.get();
        rVar.n = (C4179n) this.f10057m.get();
        rVar.o = (WrapperFramework) this.f10058n.get();
        rVar.p = (String) this.f10059o.get();
    }
}
