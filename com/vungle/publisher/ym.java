package com.vungle.publisher;

import com.vungle.publisher.cz.b;
import com.vungle.publisher.env.r;
import com.vungle.publisher.fg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ym implements MembersInjector<yk> {
    static final /* synthetic */ boolean f11350a = (!ym.class.desiredAssertionStatus());
    private final Provider<qg> f11351b;
    private final Provider<b> f11352c;
    private final Provider<a> f11353d;
    private final Provider<vc> f11354e;
    private final Provider<r> f11355f;
    private final Provider<ic.a> f11356g;

    public /* synthetic */ void injectMembers(Object obj) {
        m14163a((yk) obj);
    }

    public ym(Provider<qg> provider, Provider<b> provider2, Provider<a> provider3, Provider<vc> provider4, Provider<r> provider5, Provider<ic.a> provider6) {
        if (f11350a || provider != null) {
            this.f11351b = provider;
            if (f11350a || provider2 != null) {
                this.f11352c = provider2;
                if (f11350a || provider3 != null) {
                    this.f11353d = provider3;
                    if (f11350a || provider4 != null) {
                        this.f11354e = provider4;
                        if (f11350a || provider5 != null) {
                            this.f11355f = provider5;
                            if (f11350a || provider6 != null) {
                                this.f11356g = provider6;
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

    public static MembersInjector<yk> m14162a(Provider<qg> provider, Provider<b> provider2, Provider<a> provider3, Provider<vc> provider4, Provider<r> provider5, Provider<ic.a> provider6) {
        return new ym(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public void m14163a(yk ykVar) {
        if (ykVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ykVar.a = (qg) this.f11351b.get();
        ykVar.b = (b) this.f11352c.get();
        ykVar.c = (a) this.f11353d.get();
        ykVar.d = (vc) this.f11354e.get();
        ykVar.e = (r) this.f11355f.get();
        ykVar.f = (ic.a) this.f11356g.get();
    }
}
