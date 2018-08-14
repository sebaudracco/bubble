package com.vungle.publisher;

import com.vungle.publisher.cz.b;
import com.vungle.publisher.fg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dc implements MembersInjector<b> {
    static final /* synthetic */ boolean f9923a = (!dc.class.desiredAssertionStatus());
    private final Provider<ci> f9924b;
    private final Provider<a> f9925c;
    private final Provider<ic.a> f9926d;
    private final Provider<ki.a> f9927e;
    private final Provider<gv.a> f9928f;

    public /* synthetic */ void injectMembers(Object obj) {
        m13039a((b) obj);
    }

    public dc(Provider<ci> provider, Provider<a> provider2, Provider<ic.a> provider3, Provider<ki.a> provider4, Provider<gv.a> provider5) {
        if (f9923a || provider != null) {
            this.f9924b = provider;
            if (f9923a || provider2 != null) {
                this.f9925c = provider2;
                if (f9923a || provider3 != null) {
                    this.f9926d = provider3;
                    if (f9923a || provider4 != null) {
                        this.f9927e = provider4;
                        if (f9923a || provider5 != null) {
                            this.f9928f = provider5;
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
        throw new AssertionError();
    }

    public static MembersInjector<b> m13038a(Provider<ci> provider, Provider<a> provider2, Provider<ic.a> provider3, Provider<ki.a> provider4, Provider<gv.a> provider5) {
        return new dc(provider, provider2, provider3, provider4, provider5);
    }

    public void m13039a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (ci) this.f9924b.get();
        bVar.b = (a) this.f9925c.get();
        bVar.c = (ic.a) this.f9926d.get();
        bVar.d = (ki.a) this.f9927e.get();
        bVar.e = (gv.a) this.f9928f.get();
    }
}
