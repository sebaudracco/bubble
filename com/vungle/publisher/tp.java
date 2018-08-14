package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class tp implements MembersInjector<tn> {
    static final /* synthetic */ boolean f11101a = (!tp.class.desiredAssertionStatus());
    private final Provider<uj> f11102b;
    private final Provider<bw> f11103c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13952a((tn) obj);
    }

    public tp(Provider<uj> provider, Provider<bw> provider2) {
        if (f11101a || provider != null) {
            this.f11102b = provider;
            if (f11101a || provider2 != null) {
                this.f11103c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<tn> m13951a(Provider<uj> provider, Provider<bw> provider2) {
        return new tp(provider, provider2);
    }

    public void m13952a(tn tnVar) {
        if (tnVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        tnVar.a = (uj) this.f11102b.get();
        tnVar.b = (bw) this.f11103c.get();
    }
}
