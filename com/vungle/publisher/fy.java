package com.vungle.publisher;

import com.vungle.publisher.el.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fy implements MembersInjector<el> {
    static final /* synthetic */ boolean f10162a = (!fy.class.desiredAssertionStatus());
    private final Provider<ci> f10163b;
    private final Provider<qg> f10164c;
    private final Provider<a> f10165d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13231a((el) obj);
    }

    public fy(Provider<ci> provider, Provider<qg> provider2, Provider<a> provider3) {
        if (f10162a || provider != null) {
            this.f10163b = provider;
            if (f10162a || provider2 != null) {
                this.f10164c = provider2;
                if (f10162a || provider3 != null) {
                    this.f10165d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<el> m13230a(Provider<ci> provider, Provider<qg> provider2, Provider<a> provider3) {
        return new fy(provider, provider2, provider3);
    }

    public void m13231a(el elVar) {
        if (elVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        elVar.v = (ci) this.f10163b.get();
        elVar.F = (qg) this.f10164c.get();
        elVar.z = (a) this.f10165d.get();
    }
}
