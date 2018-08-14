package com.vungle.publisher;

import com.vungle.publisher.id.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ih implements MembersInjector<id> {
    static final /* synthetic */ boolean f10329a = (!ih.class.desiredAssertionStatus());
    private final Provider<ci> f10330b;
    private final Provider<a> f10331c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13350a((id) obj);
    }

    public ih(Provider<ci> provider, Provider<a> provider2) {
        if (f10329a || provider != null) {
            this.f10330b = provider;
            if (f10329a || provider2 != null) {
                this.f10331c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<id> m13349a(Provider<ci> provider, Provider<a> provider2) {
        return new ih(provider, provider2);
    }

    public void m13350a(id idVar) {
        if (idVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        idVar.v = (ci) this.f10330b.get();
        idVar.e = (a) this.f10331c.get();
    }
}
