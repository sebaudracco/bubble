package com.vungle.publisher;

import com.vungle.publisher.jo.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jr implements MembersInjector<a> {
    static final /* synthetic */ boolean f10577a = (!jr.class.desiredAssertionStatus());
    private final Provider<ci> f10578b;
    private final Provider<C4238m.a> f10579c;
    private final Provider<ge.a> f10580d;
    private final Provider<jo> f10581e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13543a((a) obj);
    }

    public jr(Provider<ci> provider, Provider<C4238m.a> provider2, Provider<ge.a> provider3, Provider<jo> provider4) {
        if (f10577a || provider != null) {
            this.f10578b = provider;
            if (f10577a || provider2 != null) {
                this.f10579c = provider2;
                if (f10577a || provider3 != null) {
                    this.f10580d = provider3;
                    if (f10577a || provider4 != null) {
                        this.f10581e = provider4;
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

    public static MembersInjector<a> m13542a(Provider<ci> provider, Provider<C4238m.a> provider2, Provider<ge.a> provider3, Provider<jo> provider4) {
        return new jr(provider, provider2, provider3, provider4);
    }

    public void m13543a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10578b.get();
        aVar.e = (C4238m.a) this.f10579c.get();
        aVar.a = (ge.a) this.f10580d.get();
        aVar.b = this.f10581e;
    }
}
