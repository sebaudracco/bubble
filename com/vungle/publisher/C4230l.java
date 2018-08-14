package com.vungle.publisher;

import com.vungle.publisher.c.b;
import com.vungle.publisher.hr.a;
import com.vungle.publisher.log.g;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4230l implements MembersInjector<b> {
    static final /* synthetic */ boolean f10676a = (!C4230l.class.desiredAssertionStatus());
    private final Provider<qg> f10677b;
    private final Provider<a> f10678c;
    private final Provider<g> f10679d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13603a((b) obj);
    }

    public C4230l(Provider<qg> provider, Provider<a> provider2, Provider<g> provider3) {
        if (f10676a || provider != null) {
            this.f10677b = provider;
            if (f10676a || provider2 != null) {
                this.f10678c = provider2;
                if (f10676a || provider3 != null) {
                    this.f10679d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<b> m13602a(Provider<qg> provider, Provider<a> provider2, Provider<g> provider3) {
        return new C4230l(provider, provider2, provider3);
    }

    public void m13603a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.eventBus = (qg) this.f10677b.get();
        bVar.d = (a) this.f10678c.get();
        bVar.e = (g) this.f10679d.get();
    }
}
