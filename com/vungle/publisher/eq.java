package com.vungle.publisher;

import com.vungle.publisher.df.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class eq implements MembersInjector<em> {
    static final /* synthetic */ boolean f10070a = (!eq.class.desiredAssertionStatus());
    private final Provider<ci> f10071b;
    private final Provider<a> f10072c;
    private final Provider<ge> f10073d;
    private final Provider<el.a> f10074e;
    private final Provider<em.a> f10075f;
    private final Provider<lb.a> f10076g;

    public /* synthetic */ void injectMembers(Object obj) {
        m13175a((em) obj);
    }

    public eq(Provider<ci> provider, Provider<a> provider2, Provider<ge> provider3, Provider<el.a> provider4, Provider<em.a> provider5, Provider<lb.a> provider6) {
        if (f10070a || provider != null) {
            this.f10071b = provider;
            if (f10070a || provider2 != null) {
                this.f10072c = provider2;
                if (f10070a || provider3 != null) {
                    this.f10073d = provider3;
                    if (f10070a || provider4 != null) {
                        this.f10074e = provider4;
                        if (f10070a || provider5 != null) {
                            this.f10075f = provider5;
                            if (f10070a || provider6 != null) {
                                this.f10076g = provider6;
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

    public static MembersInjector<em> m13174a(Provider<ci> provider, Provider<a> provider2, Provider<ge> provider3, Provider<el.a> provider4, Provider<em.a> provider5, Provider<lb.a> provider6) {
        return new eq(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public void m13175a(em emVar) {
        if (emVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        emVar.v = (ci) this.f10071b.get();
        emVar.e = (a) this.f10072c.get();
        emVar.f = (ge) this.f10073d.get();
        emVar.g = (el.a) this.f10074e.get();
        emVar.h = (em.a) this.f10075f.get();
        emVar.i = (lb.a) this.f10076g.get();
    }
}
