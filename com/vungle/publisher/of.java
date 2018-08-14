package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.env.i;
import com.vungle.publisher.lf.b;
import com.vungle.publisher.om.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class of implements MembersInjector<ob> {
    static final /* synthetic */ boolean f10846a = (!of.class.desiredAssertionStatus());
    private final Provider<mq> f10847b;
    private final Provider<i> f10848c;
    private final Provider<qg> f10849d;
    private final Provider<b> f10850e;
    private final Provider<Context> f10851f;
    private final Provider<a> f10852g;

    public /* synthetic */ void injectMembers(Object obj) {
        m13778a((ob) obj);
    }

    public of(Provider<mq> provider, Provider<i> provider2, Provider<qg> provider3, Provider<b> provider4, Provider<Context> provider5, Provider<a> provider6) {
        if (f10846a || provider != null) {
            this.f10847b = provider;
            if (f10846a || provider2 != null) {
                this.f10848c = provider2;
                if (f10846a || provider3 != null) {
                    this.f10849d = provider3;
                    if (f10846a || provider4 != null) {
                        this.f10850e = provider4;
                        if (f10846a || provider5 != null) {
                            this.f10851f = provider5;
                            if (f10846a || provider6 != null) {
                                this.f10852g = provider6;
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

    public static MembersInjector<ob> m13777a(Provider<mq> provider, Provider<i> provider2, Provider<qg> provider3, Provider<b> provider4, Provider<Context> provider5, Provider<a> provider6) {
        return new of(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public void m13778a(ob obVar) {
        if (obVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        obVar.d = (mq) this.f10847b.get();
        obVar.j = (i) this.f10848c.get();
        obVar.k = (qg) this.f10849d.get();
        obVar.l = (b) this.f10850e.get();
        obVar.m = (Context) this.f10851f.get();
        obVar.n = (a) this.f10852g.get();
    }
}
