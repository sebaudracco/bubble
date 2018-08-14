package com.vungle.publisher;

import com.vungle.publisher.env.i;
import com.vungle.publisher.env.o;
import com.vungle.publisher.oy.a.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class pa implements MembersInjector<oy> {
    static final /* synthetic */ boolean f10902a = (!pa.class.desiredAssertionStatus());
    private final Provider<qg> f10903b;
    private final Provider<zc> f10904c;
    private final Provider<i> f10905d;
    private final Provider<a> f10906e;
    private final Provider<ys.a> f10907f;
    private final Provider<op.a> f10908g;
    private final Provider<ob.a> f10909h;
    private final Provider<o> f10910i;

    public /* synthetic */ void injectMembers(Object obj) {
        m13811a((oy) obj);
    }

    public pa(Provider<qg> provider, Provider<zc> provider2, Provider<i> provider3, Provider<a> provider4, Provider<ys.a> provider5, Provider<op.a> provider6, Provider<ob.a> provider7, Provider<o> provider8) {
        if (f10902a || provider != null) {
            this.f10903b = provider;
            if (f10902a || provider2 != null) {
                this.f10904c = provider2;
                if (f10902a || provider3 != null) {
                    this.f10905d = provider3;
                    if (f10902a || provider4 != null) {
                        this.f10906e = provider4;
                        if (f10902a || provider5 != null) {
                            this.f10907f = provider5;
                            if (f10902a || provider6 != null) {
                                this.f10908g = provider6;
                                if (f10902a || provider7 != null) {
                                    this.f10909h = provider7;
                                    if (f10902a || provider8 != null) {
                                        this.f10910i = provider8;
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

    public static MembersInjector<oy> m13810a(Provider<qg> provider, Provider<zc> provider2, Provider<i> provider3, Provider<a> provider4, Provider<ys.a> provider5, Provider<op.a> provider6, Provider<ob.a> provider7, Provider<o> provider8) {
        return new pa(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public void m13811a(oy oyVar) {
        if (oyVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        oyVar.i = (qg) this.f10903b.get();
        oyVar.j = (zc) this.f10904c.get();
        oyVar.k = (i) this.f10905d.get();
        oyVar.l = (a) this.f10906e.get();
        oyVar.m = (ys.a) this.f10907f.get();
        oyVar.n = (op.a) this.f10908g.get();
        oyVar.o = (ob.a) this.f10909h.get();
        oyVar.p = (o) this.f10910i.get();
    }
}
