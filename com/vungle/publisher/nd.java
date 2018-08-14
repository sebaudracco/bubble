package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.env.i;
import com.vungle.publisher.lf.b;
import com.vungle.publisher.rf.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nd implements MembersInjector<my> {
    static final /* synthetic */ boolean f10784a = (!nd.class.desiredAssertionStatus());
    private final Provider<mq> f10785b;
    private final Provider<i> f10786c;
    private final Provider<qg> f10787d;
    private final Provider<b> f10788e;
    private final Provider<Context> f10789f;
    private final Provider<a> f10790g;
    private final Provider<my.b.a> f10791h;
    private final Provider<rr> f10792i;
    private final Provider<ro.a> f10793j;
    private final Provider<og.a> f10794k;

    public /* synthetic */ void injectMembers(Object obj) {
        m13738a((my) obj);
    }

    public nd(Provider<mq> provider, Provider<i> provider2, Provider<qg> provider3, Provider<b> provider4, Provider<Context> provider5, Provider<a> provider6, Provider<my.b.a> provider7, Provider<rr> provider8, Provider<ro.a> provider9, Provider<og.a> provider10) {
        if (f10784a || provider != null) {
            this.f10785b = provider;
            if (f10784a || provider2 != null) {
                this.f10786c = provider2;
                if (f10784a || provider3 != null) {
                    this.f10787d = provider3;
                    if (f10784a || provider4 != null) {
                        this.f10788e = provider4;
                        if (f10784a || provider5 != null) {
                            this.f10789f = provider5;
                            if (f10784a || provider6 != null) {
                                this.f10790g = provider6;
                                if (f10784a || provider7 != null) {
                                    this.f10791h = provider7;
                                    if (f10784a || provider8 != null) {
                                        this.f10792i = provider8;
                                        if (f10784a || provider9 != null) {
                                            this.f10793j = provider9;
                                            if (f10784a || provider10 != null) {
                                                this.f10794k = provider10;
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

    public static MembersInjector<my> m13737a(Provider<mq> provider, Provider<i> provider2, Provider<qg> provider3, Provider<b> provider4, Provider<Context> provider5, Provider<a> provider6, Provider<my.b.a> provider7, Provider<rr> provider8, Provider<ro.a> provider9, Provider<og.a> provider10) {
        return new nd(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public void m13738a(my myVar) {
        if (myVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        myVar.d = (mq) this.f10785b.get();
        myVar.j = (i) this.f10786c.get();
        myVar.k = (qg) this.f10787d.get();
        myVar.l = (b) this.f10788e.get();
        myVar.m = (Context) this.f10789f.get();
        myVar.s = (a) this.f10790g.get();
        myVar.t = (my.b.a) this.f10791h.get();
        myVar.u = (rr) this.f10792i.get();
        myVar.v = (ro.a) this.f10793j.get();
        myVar.w = (og.a) this.f10794k.get();
    }
}
