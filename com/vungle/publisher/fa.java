package com.vungle.publisher;

import com.vungle.publisher.ew.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fa implements MembersInjector<ew> {
    static final /* synthetic */ boolean f10096a = (!fa.class.desiredAssertionStatus());
    private final Provider<ci> f10097b;
    private final Provider<a> f10098c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13191a((ew) obj);
    }

    public fa(Provider<ci> provider, Provider<a> provider2) {
        if (f10096a || provider != null) {
            this.f10097b = provider;
            if (f10096a || provider2 != null) {
                this.f10098c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ew> m13190a(Provider<ci> provider, Provider<a> provider2) {
        return new fa(provider, provider2);
    }

    public void m13191a(ew ewVar) {
        if (ewVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ewVar.v = (ci) this.f10097b.get();
        ewVar.d = (a) this.f10098c.get();
    }
}
