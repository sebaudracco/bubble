package com.vungle.publisher;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class lr implements MembersInjector<lp> {
    static final /* synthetic */ boolean f10725a = (!lr.class.desiredAssertionStatus());
    private final Provider<Context> f10726b;
    private final Provider<qg> f10727c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13665a((lp) obj);
    }

    public lr(Provider<Context> provider, Provider<qg> provider2) {
        if (f10725a || provider != null) {
            this.f10726b = provider;
            if (f10725a || provider2 != null) {
                this.f10727c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<lp> m13664a(Provider<Context> provider, Provider<qg> provider2) {
        return new lr(provider, provider2);
    }

    public void m13665a(lp lpVar) {
        if (lpVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        lpVar.a = (Context) this.f10726b.get();
        lpVar.b = (qg) this.f10727c.get();
    }
}
