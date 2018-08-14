package com.vungle.publisher;

import com.vungle.publisher.el.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gc implements MembersInjector<ek> {
    static final /* synthetic */ boolean f10177a = (!gc.class.desiredAssertionStatus());
    private final Provider<ci> f10178b;
    private final Provider<a> f10179c;
    private final Provider<ek.a> f10180d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13241a((ek) obj);
    }

    public gc(Provider<ci> provider, Provider<a> provider2, Provider<ek.a> provider3) {
        if (f10177a || provider != null) {
            this.f10178b = provider;
            if (f10177a || provider2 != null) {
                this.f10179c = provider2;
                if (f10177a || provider3 != null) {
                    this.f10180d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ek> m13240a(Provider<ci> provider, Provider<a> provider2, Provider<ek.a> provider3) {
        return new gc(provider, provider2, provider3);
    }

    public void m13241a(ek ekVar) {
        if (ekVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ekVar.v = (ci) this.f10178b.get();
        ekVar.c = (a) this.f10179c.get();
        ekVar.d = (ek.a) this.f10180d.get();
    }
}
