package com.vungle.publisher;

import com.vungle.publisher.fg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fo implements MembersInjector<a> {
    static final /* synthetic */ boolean f10124a = (!fo.class.desiredAssertionStatus());
    private final Provider<ci> f10125b;
    private final Provider<co.a> f10126c;
    private final Provider<ct.a> f10127d;
    private final Provider<el.a> f10128e;
    private final Provider<fb.a> f10129f;
    private final Provider<fg> f10130g;
    private final Provider<ds.a> f10131h;

    public /* synthetic */ void injectMembers(Object obj) {
        m13213a((a) obj);
    }

    public fo(Provider<ci> provider, Provider<co.a> provider2, Provider<ct.a> provider3, Provider<el.a> provider4, Provider<fb.a> provider5, Provider<fg> provider6, Provider<ds.a> provider7) {
        if (f10124a || provider != null) {
            this.f10125b = provider;
            if (f10124a || provider2 != null) {
                this.f10126c = provider2;
                if (f10124a || provider3 != null) {
                    this.f10127d = provider3;
                    if (f10124a || provider4 != null) {
                        this.f10128e = provider4;
                        if (f10124a || provider5 != null) {
                            this.f10129f = provider5;
                            if (f10124a || provider6 != null) {
                                this.f10130g = provider6;
                                if (f10124a || provider7 != null) {
                                    this.f10131h = provider7;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13212a(Provider<ci> provider, Provider<co.a> provider2, Provider<ct.a> provider3, Provider<el.a> provider4, Provider<fb.a> provider5, Provider<fg> provider6, Provider<ds.a> provider7) {
        return new fo(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public void m13213a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10125b.get();
        aVar.a = (co.a) this.f10126c.get();
        aVar.b = (ct.a) this.f10127d.get();
        aVar.c = (el.a) this.f10128e.get();
        aVar.e = (fb.a) this.f10129f.get();
        aVar.f = this.f10130g;
        aVar.g = (ds.a) this.f10131h.get();
    }
}
