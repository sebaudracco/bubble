package com.vungle.publisher;

import com.vungle.publisher.env.C4181i;
import com.vungle.publisher.env.r;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gi implements MembersInjector<ge> {
    static final /* synthetic */ boolean f10187a = (!gi.class.desiredAssertionStatus());
    private final Provider<C4181i> f10188b;
    private final Provider<r> f10189c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13259a((ge) obj);
    }

    public gi(Provider<C4181i> provider, Provider<r> provider2) {
        if (f10187a || provider != null) {
            this.f10188b = provider;
            if (f10187a || provider2 != null) {
                this.f10189c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ge> m13258a(Provider<C4181i> provider, Provider<r> provider2) {
        return new gi(provider, provider2);
    }

    public void m13259a(ge geVar) {
        if (geVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        geVar.d = (C4181i) this.f10188b.get();
        geVar.e = (r) this.f10189c.get();
    }
}
