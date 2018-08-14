package com.vungle.publisher;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class mx implements MembersInjector<mv> {
    static final /* synthetic */ boolean f10768a = (!mx.class.desiredAssertionStatus());
    private final Provider<Context> f10769b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13717a((mv) obj);
    }

    public mx(Provider<Context> provider) {
        if (f10768a || provider != null) {
            this.f10769b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<mv> m13716a(Provider<Context> provider) {
        return new mx(provider);
    }

    public void m13717a(mv mvVar) {
        if (mvVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        mvVar.a = (Context) this.f10769b.get();
    }
}
