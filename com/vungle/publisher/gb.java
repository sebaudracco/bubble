package com.vungle.publisher;

import com.vungle.publisher.ek.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gb implements MembersInjector<a> {
    static final /* synthetic */ boolean f10172a = (!gb.class.desiredAssertionStatus());
    private final Provider<ci> f10173b;
    private final Provider<m.a> f10174c;
    private final Provider<ek> f10175d;
    private final Provider<ge.a> f10176e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13239a((a) obj);
    }

    public gb(Provider<ci> provider, Provider<m.a> provider2, Provider<ek> provider3, Provider<ge.a> provider4) {
        if (f10172a || provider != null) {
            this.f10173b = provider;
            if (f10172a || provider2 != null) {
                this.f10174c = provider2;
                if (f10172a || provider3 != null) {
                    this.f10175d = provider3;
                    if (f10172a || provider4 != null) {
                        this.f10176e = provider4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13238a(Provider<ci> provider, Provider<m.a> provider2, Provider<ek> provider3, Provider<ge.a> provider4) {
        return new gb(provider, provider2, provider3, provider4);
    }

    public void m13239a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10173b.get();
        aVar.e = (m.a) this.f10174c.get();
        aVar.a = this.f10175d;
        aVar.b = (ge.a) this.f10176e.get();
    }
}
