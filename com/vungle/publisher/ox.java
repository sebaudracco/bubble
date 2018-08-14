package com.vungle.publisher;

import com.vungle.publisher.env.i;
import com.vungle.publisher.op.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ox implements MembersInjector<op> {
    static final /* synthetic */ boolean f10887a = (!ox.class.desiredAssertionStatus());
    private final Provider<mq> f10888b;
    private final Provider<mv> f10889c;
    private final Provider<qg> f10890d;
    private final Provider<a> f10891e;
    private final Provider<og.a> f10892f;
    private final Provider<oj.a> f10893g;
    private final Provider<i> f10894h;
    private final Provider<zo> f10895i;
    private final Provider<cb> f10896j;
    private final Provider<lm> f10897k;
    private final Provider<zf> f10898l;

    public /* synthetic */ void injectMembers(Object obj) {
        m13807a((op) obj);
    }

    public ox(Provider<mq> provider, Provider<mv> provider2, Provider<qg> provider3, Provider<a> provider4, Provider<og.a> provider5, Provider<oj.a> provider6, Provider<i> provider7, Provider<zo> provider8, Provider<cb> provider9, Provider<lm> provider10, Provider<zf> provider11) {
        if (f10887a || provider != null) {
            this.f10888b = provider;
            if (f10887a || provider2 != null) {
                this.f10889c = provider2;
                if (f10887a || provider3 != null) {
                    this.f10890d = provider3;
                    if (f10887a || provider4 != null) {
                        this.f10891e = provider4;
                        if (f10887a || provider5 != null) {
                            this.f10892f = provider5;
                            if (f10887a || provider6 != null) {
                                this.f10893g = provider6;
                                if (f10887a || provider7 != null) {
                                    this.f10894h = provider7;
                                    if (f10887a || provider8 != null) {
                                        this.f10895i = provider8;
                                        if (f10887a || provider9 != null) {
                                            this.f10896j = provider9;
                                            if (f10887a || provider10 != null) {
                                                this.f10897k = provider10;
                                                if (f10887a || provider11 != null) {
                                                    this.f10898l = provider11;
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

    public static MembersInjector<op> m13806a(Provider<mq> provider, Provider<mv> provider2, Provider<qg> provider3, Provider<a> provider4, Provider<og.a> provider5, Provider<oj.a> provider6, Provider<i> provider7, Provider<zo> provider8, Provider<cb> provider9, Provider<lm> provider10, Provider<zf> provider11) {
        return new ox(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public void m13807a(op opVar) {
        if (opVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        opVar.d = (mq) this.f10888b.get();
        opVar.g = (mv) this.f10889c.get();
        opVar.h = (qg) this.f10890d.get();
        opVar.i = (a) this.f10891e.get();
        opVar.j = (og.a) this.f10892f.get();
        opVar.k = (oj.a) this.f10893g.get();
        opVar.l = (i) this.f10894h.get();
        opVar.m = (zo) this.f10895i.get();
        opVar.n = (cb) this.f10896j.get();
        opVar.o = (lm) this.f10897k.get();
        opVar.p = (zf) this.f10898l.get();
    }
}
