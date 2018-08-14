package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.c.a;
import com.vungle.publisher.c.b;
import com.vungle.publisher.env.C4181i;
import com.vungle.publisher.env.k;
import com.vungle.publisher.env.o;
import com.vungle.publisher.env.r;
import com.vungle.publisher.log.g;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4191h implements MembersInjector<c> {
    static final /* synthetic */ boolean f10220a = (!C4191h.class.desiredAssertionStatus());
    private final Provider<qg> f10221b;
    private final Provider<r> f10222c;
    private final Provider<Context> f10223d;
    private final Provider<C4181i> f10224e;
    private final Provider<Class> f10225f;
    private final Provider<Class> f10226g;
    private final Provider<Class> f10227h;
    private final Provider<bw> f10228i;
    private final Provider<sz> f10229j;
    private final Provider<a> f10230k;
    private final Provider<b> f10231l;
    private final Provider<vc> f10232m;
    private final Provider<o> f10233n;
    private final Provider<u> f10234o;
    private final Provider<k> f10235p;
    private final Provider<eb.b> f10236q;
    private final Provider<xg> f10237r;
    private final Provider<g> f10238s;

    public /* synthetic */ void injectMembers(Object obj) {
        m13283a((c) obj);
    }

    public C4191h(Provider<qg> provider, Provider<r> provider2, Provider<Context> provider3, Provider<C4181i> provider4, Provider<Class> provider5, Provider<Class> provider6, Provider<Class> provider7, Provider<bw> provider8, Provider<sz> provider9, Provider<a> provider10, Provider<b> provider11, Provider<vc> provider12, Provider<o> provider13, Provider<u> provider14, Provider<k> provider15, Provider<eb.b> provider16, Provider<xg> provider17, Provider<g> provider18) {
        if (f10220a || provider != null) {
            this.f10221b = provider;
            if (f10220a || provider2 != null) {
                this.f10222c = provider2;
                if (f10220a || provider3 != null) {
                    this.f10223d = provider3;
                    if (f10220a || provider4 != null) {
                        this.f10224e = provider4;
                        if (f10220a || provider5 != null) {
                            this.f10225f = provider5;
                            if (f10220a || provider6 != null) {
                                this.f10226g = provider6;
                                if (f10220a || provider7 != null) {
                                    this.f10227h = provider7;
                                    if (f10220a || provider8 != null) {
                                        this.f10228i = provider8;
                                        if (f10220a || provider9 != null) {
                                            this.f10229j = provider9;
                                            if (f10220a || provider10 != null) {
                                                this.f10230k = provider10;
                                                if (f10220a || provider11 != null) {
                                                    this.f10231l = provider11;
                                                    if (f10220a || provider12 != null) {
                                                        this.f10232m = provider12;
                                                        if (f10220a || provider13 != null) {
                                                            this.f10233n = provider13;
                                                            if (f10220a || provider14 != null) {
                                                                this.f10234o = provider14;
                                                                if (f10220a || provider15 != null) {
                                                                    this.f10235p = provider15;
                                                                    if (f10220a || provider16 != null) {
                                                                        this.f10236q = provider16;
                                                                        if (f10220a || provider17 != null) {
                                                                            this.f10237r = provider17;
                                                                            if (f10220a || provider18 != null) {
                                                                                this.f10238s = provider18;
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
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<c> m13282a(Provider<qg> provider, Provider<r> provider2, Provider<Context> provider3, Provider<C4181i> provider4, Provider<Class> provider5, Provider<Class> provider6, Provider<Class> provider7, Provider<bw> provider8, Provider<sz> provider9, Provider<a> provider10, Provider<b> provider11, Provider<vc> provider12, Provider<o> provider13, Provider<u> provider14, Provider<k> provider15, Provider<eb.b> provider16, Provider<xg> provider17, Provider<g> provider18) {
        return new C4191h(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18);
    }

    public void m13283a(c cVar) {
        if (cVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        cVar.eventBus = (qg) this.f10221b.get();
        cVar.a = (r) this.f10222c.get();
        cVar.b = (Context) this.f10223d.get();
        cVar.c = (C4181i) this.f10224e.get();
        cVar.d = (qg) this.f10221b.get();
        cVar.e = (Class) this.f10225f.get();
        cVar.f = (Class) this.f10226g.get();
        cVar.g = (Class) this.f10227h.get();
        cVar.h = (bw) this.f10228i.get();
        cVar.i = (sz) this.f10229j.get();
        cVar.j = DoubleCheck.lazy(this.f10230k);
        cVar.k = this.f10231l;
        cVar.l = (vc) this.f10232m.get();
        cVar.m = (o) this.f10233n.get();
        cVar.n = (u) this.f10234o.get();
        cVar.o = (k) this.f10235p.get();
        cVar.p = (eb.b) this.f10236q.get();
        cVar.q = (xg) this.f10237r.get();
        cVar.r = (g) this.f10238s.get();
    }
}
