package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class qe implements MembersInjector<py> {
    static final /* synthetic */ boolean f10937a = (!qe.class.desiredAssertionStatus());
    private final Provider<qg> f10938b;
    private final Provider<bw> f10939c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13835a((py) obj);
    }

    public qe(Provider<qg> provider, Provider<bw> provider2) {
        if (f10937a || provider != null) {
            this.f10938b = provider;
            if (f10937a || provider2 != null) {
                this.f10939c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<py> m13834a(Provider<qg> provider, Provider<bw> provider2) {
        return new qe(provider, provider2);
    }

    public void m13835a(py pyVar) {
        if (pyVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        pyVar.eventBus = (qg) this.f10938b.get();
        pyVar.b = (bw) this.f10939c.get();
    }
}
