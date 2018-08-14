package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.env.C4179n;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ch implements MembersInjector<cf> {
    static final /* synthetic */ boolean f9840a = (!ch.class.desiredAssertionStatus());
    private final Provider<Context> f9841b;
    private final Provider<ci> f9842c;
    private final Provider<C4179n> f9843d;

    public /* synthetic */ void injectMembers(Object obj) {
        m12929a((cf) obj);
    }

    public ch(Provider<Context> provider, Provider<ci> provider2, Provider<C4179n> provider3) {
        if (f9840a || provider != null) {
            this.f9841b = provider;
            if (f9840a || provider2 != null) {
                this.f9842c = provider2;
                if (f9840a || provider3 != null) {
                    this.f9843d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<cf> m12928a(Provider<Context> provider, Provider<ci> provider2, Provider<C4179n> provider3) {
        return new ch(provider, provider2, provider3);
    }

    public void m12929a(cf cfVar) {
        if (cfVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        cfVar.a = (Context) this.f9841b.get();
        cfVar.b = (ci) this.f9842c.get();
        cfVar.c = (C4179n) this.f9843d.get();
    }
}
