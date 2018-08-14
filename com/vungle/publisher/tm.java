package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class tm implements MembersInjector<tj> {
    static final /* synthetic */ boolean f11096a = (!tm.class.desiredAssertionStatus());
    private final Provider<uq> f11097b;
    private final Provider<bw> f11098c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13948a((tj) obj);
    }

    public tm(Provider<uq> provider, Provider<bw> provider2) {
        if (f11096a || provider != null) {
            this.f11097b = provider;
            if (f11096a || provider2 != null) {
                this.f11098c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<tj> m13947a(Provider<uq> provider, Provider<bw> provider2) {
        return new tm(provider, provider2);
    }

    public void m13948a(tj tjVar) {
        if (tjVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        tjVar.a = (uq) this.f11097b.get();
        tjVar.b = (bw) this.f11098c.get();
    }
}
