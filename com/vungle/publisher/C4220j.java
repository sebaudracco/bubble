package com.vungle.publisher;

import com.vungle.publisher.c.a;
import com.vungle.publisher.log.g;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4220j implements MembersInjector<a> {
    static final /* synthetic */ boolean f10510a = (!C4220j.class.desiredAssertionStatus());
    private final Provider<qg> f10511b;
    private final Provider<g> f10512c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13460a((a) obj);
    }

    public C4220j(Provider<qg> provider, Provider<g> provider2) {
        if (f10510a || provider != null) {
            this.f10511b = provider;
            if (f10510a || provider2 != null) {
                this.f10512c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13459a(Provider<qg> provider, Provider<g> provider2) {
        return new C4220j(provider, provider2);
    }

    public void m13460a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.eventBus = (qg) this.f10511b.get();
        aVar.b = (g) this.f10512c.get();
    }
}
