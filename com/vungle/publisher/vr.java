package com.vungle.publisher;

import com.vungle.publisher.env.o;
import com.vungle.publisher.env.r;
import com.vungle.publisher.log.g;
import com.vungle.publisher.wm.a;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

/* compiled from: vungle */
public final class vr implements MembersInjector<vc> {
    static final /* synthetic */ boolean f11175a = (!vr.class.desiredAssertionStatus());
    private final Provider<qg> f11176b;
    private final Provider<bw> f11177c;
    private final Provider<yk> f11178d;
    private final Provider<o> f11179e;
    private final Provider<zf> f11180f;
    private final Provider<a> f11181g;
    private final Provider<r> f11182h;
    private final Provider<String> f11183i;
    private final Provider<g> f11184j;
    private final Provider<yd> f11185k;
    private final Provider<hk.a> f11186l;

    public /* synthetic */ void injectMembers(Object obj) {
        m14009a((vc) obj);
    }

    public vr(Provider<qg> provider, Provider<bw> provider2, Provider<yk> provider3, Provider<o> provider4, Provider<zf> provider5, Provider<a> provider6, Provider<r> provider7, Provider<String> provider8, Provider<g> provider9, Provider<yd> provider10, Provider<hk.a> provider11) {
        if (f11175a || provider != null) {
            this.f11176b = provider;
            if (f11175a || provider2 != null) {
                this.f11177c = provider2;
                if (f11175a || provider3 != null) {
                    this.f11178d = provider3;
                    if (f11175a || provider4 != null) {
                        this.f11179e = provider4;
                        if (f11175a || provider5 != null) {
                            this.f11180f = provider5;
                            if (f11175a || provider6 != null) {
                                this.f11181g = provider6;
                                if (f11175a || provider7 != null) {
                                    this.f11182h = provider7;
                                    if (f11175a || provider8 != null) {
                                        this.f11183i = provider8;
                                        if (f11175a || provider9 != null) {
                                            this.f11184j = provider9;
                                            if (f11175a || provider10 != null) {
                                                this.f11185k = provider10;
                                                if (f11175a || provider11 != null) {
                                                    this.f11186l = provider11;
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

    public static MembersInjector<vc> m14008a(Provider<qg> provider, Provider<bw> provider2, Provider<yk> provider3, Provider<o> provider4, Provider<zf> provider5, Provider<a> provider6, Provider<r> provider7, Provider<String> provider8, Provider<g> provider9, Provider<yd> provider10, Provider<hk.a> provider11) {
        return new vr(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public void m14009a(vc vcVar) {
        if (vcVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        vcVar.a = (qg) this.f11176b.get();
        vcVar.b = (bw) this.f11177c.get();
        vcVar.c = DoubleCheck.lazy(this.f11178d);
        vcVar.d = (o) this.f11179e.get();
        vcVar.e = (zf) this.f11180f.get();
        vcVar.f = (a) this.f11181g.get();
        vcVar.g = DoubleCheck.lazy(this.f11182h);
        vcVar.h = (String) this.f11183i.get();
        vcVar.i = (g) this.f11184j.get();
        vcVar.j = (yd) this.f11185k.get();
        vcVar.k = (hk.a) this.f11186l.get();
    }
}
