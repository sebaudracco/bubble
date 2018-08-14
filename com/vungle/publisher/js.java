package com.vungle.publisher;

import com.vungle.publisher.df.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class js implements MembersInjector<jo> {
    static final /* synthetic */ boolean f10582a = (!js.class.desiredAssertionStatus());
    private final Provider<ci> f10583b;
    private final Provider<a> f10584c;
    private final Provider<ge> f10585d;
    private final Provider<jn.a> f10586e;
    private final Provider<jo.a> f10587f;

    public /* synthetic */ void injectMembers(Object obj) {
        m13545a((jo) obj);
    }

    public js(Provider<ci> provider, Provider<a> provider2, Provider<ge> provider3, Provider<jn.a> provider4, Provider<jo.a> provider5) {
        if (f10582a || provider != null) {
            this.f10583b = provider;
            if (f10582a || provider2 != null) {
                this.f10584c = provider2;
                if (f10582a || provider3 != null) {
                    this.f10585d = provider3;
                    if (f10582a || provider4 != null) {
                        this.f10586e = provider4;
                        if (f10582a || provider5 != null) {
                            this.f10587f = provider5;
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

    public static MembersInjector<jo> m13544a(Provider<ci> provider, Provider<a> provider2, Provider<ge> provider3, Provider<jn.a> provider4, Provider<jo.a> provider5) {
        return new js(provider, provider2, provider3, provider4, provider5);
    }

    public void m13545a(jo joVar) {
        if (joVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        joVar.v = (ci) this.f10583b.get();
        joVar.e = (a) this.f10584c.get();
        joVar.f = (ge) this.f10585d.get();
        joVar.g = (jn.a) this.f10586e.get();
        joVar.h = (jo.a) this.f10587f.get();
    }
}
