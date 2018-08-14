package com.vungle.publisher;

import com.vungle.publisher.co.a;
import com.vungle.publisher.cz.b;
import com.vungle.publisher.env.o;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class yr implements MembersInjector<yn> {
    static final /* synthetic */ boolean f11363a = (!yr.class.desiredAssertionStatus());
    private final Provider<qg> f11364b;
    private final Provider<b> f11365c;
    private final Provider<yk> f11366d;
    private final Provider<tj> f11367e;
    private final Provider<o> f11368f;
    private final Provider<a> f11369g;

    public /* synthetic */ void injectMembers(Object obj) {
        m14171a((yn) obj);
    }

    public yr(Provider<qg> provider, Provider<b> provider2, Provider<yk> provider3, Provider<tj> provider4, Provider<o> provider5, Provider<a> provider6) {
        if (f11363a || provider != null) {
            this.f11364b = provider;
            if (f11363a || provider2 != null) {
                this.f11365c = provider2;
                if (f11363a || provider3 != null) {
                    this.f11366d = provider3;
                    if (f11363a || provider4 != null) {
                        this.f11367e = provider4;
                        if (f11363a || provider5 != null) {
                            this.f11368f = provider5;
                            if (f11363a || provider6 != null) {
                                this.f11369g = provider6;
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

    public static MembersInjector<yn> m14170a(Provider<qg> provider, Provider<b> provider2, Provider<yk> provider3, Provider<tj> provider4, Provider<o> provider5, Provider<a> provider6) {
        return new yr(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public void m14171a(yn ynVar) {
        if (ynVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ynVar.eventBus = (qg) this.f11364b.get();
        ynVar.d = (b) this.f11365c.get();
        ynVar.e = (yk) this.f11366d.get();
        ynVar.f = (tj) this.f11367e.get();
        ynVar.g = (o) this.f11368f.get();
        ynVar.h = (a) this.f11369g.get();
    }
}
