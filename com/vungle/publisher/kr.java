package com.vungle.publisher;

import com.vungle.publisher.ki.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kr implements MembersInjector<ki> {
    static final /* synthetic */ boolean f10645a = (!kr.class.desiredAssertionStatus());
    private final Provider<ci> f10646b;
    private final Provider<zl> f10647c;
    private final Provider<a> f10648d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13587a((ki) obj);
    }

    public kr(Provider<ci> provider, Provider<zl> provider2, Provider<a> provider3) {
        if (f10645a || provider != null) {
            this.f10646b = provider;
            if (f10645a || provider2 != null) {
                this.f10647c = provider2;
                if (f10645a || provider3 != null) {
                    this.f10648d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ki> m13586a(Provider<ci> provider, Provider<zl> provider2, Provider<a> provider3) {
        return new kr(provider, provider2, provider3);
    }

    public void m13587a(ki kiVar) {
        if (kiVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        kiVar.v = (ci) this.f10646b.get();
        kiVar.r = (zl) this.f10647c.get();
        kiVar.x = (a) this.f10648d.get();
    }
}
