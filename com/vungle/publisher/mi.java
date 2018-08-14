package com.vungle.publisher;

import com.vungle.publisher.mg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class mi implements MembersInjector<a> {
    static final /* synthetic */ boolean f10749a = (!mi.class.desiredAssertionStatus());
    private final Provider<oy> f10750b;
    private final Provider<nk> f10751c;
    private final Provider<ns> f10752d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13695a((a) obj);
    }

    public mi(Provider<oy> provider, Provider<nk> provider2, Provider<ns> provider3) {
        if (f10749a || provider != null) {
            this.f10750b = provider;
            if (f10749a || provider2 != null) {
                this.f10751c = provider2;
                if (f10749a || provider3 != null) {
                    this.f10752d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13694a(Provider<oy> provider, Provider<nk> provider2, Provider<ns> provider3) {
        return new mi(provider, provider2, provider3);
    }

    public void m13695a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10750b;
        aVar.b = this.f10751c;
        aVar.c = this.f10752d;
    }
}
