package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ly implements MembersInjector<lw> {
    static final /* synthetic */ boolean f10731a = (!ly.class.desiredAssertionStatus());
    private final Provider<qg> f10732b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13671a((lw) obj);
    }

    public ly(Provider<qg> provider) {
        if (f10731a || provider != null) {
            this.f10732b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<lw> m13670a(Provider<qg> provider) {
        return new ly(provider);
    }

    public void m13671a(lw lwVar) {
        if (lwVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        lwVar.a = (qg) this.f10732b.get();
    }
}
