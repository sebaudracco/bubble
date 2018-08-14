package com.vungle.publisher;

import com.vungle.publisher.fg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fp implements MembersInjector<fg> {
    static final /* synthetic */ boolean f10132a = (!fp.class.desiredAssertionStatus());
    private final Provider<ci> f10133b;
    private final Provider<zl> f10134c;
    private final Provider<a> f10135d;
    private final Provider<fb.a> f10136e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13215a((fg) obj);
    }

    public fp(Provider<ci> provider, Provider<zl> provider2, Provider<a> provider3, Provider<fb.a> provider4) {
        if (f10132a || provider != null) {
            this.f10133b = provider;
            if (f10132a || provider2 != null) {
                this.f10134c = provider2;
                if (f10132a || provider3 != null) {
                    this.f10135d = provider3;
                    if (f10132a || provider4 != null) {
                        this.f10136e = provider4;
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

    public static MembersInjector<fg> m13214a(Provider<ci> provider, Provider<zl> provider2, Provider<a> provider3, Provider<fb.a> provider4) {
        return new fp(provider, provider2, provider3, provider4);
    }

    public void m13215a(fg fgVar) {
        if (fgVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        fgVar.v = (ci) this.f10133b.get();
        fgVar.r = (zl) this.f10134c.get();
        fgVar.s = (a) this.f10135d.get();
        fgVar.w = (fb.a) this.f10136e.get();
    }
}
