package com.vungle.publisher;

import com.vungle.publisher.cz.b;
import com.vungle.publisher.env.o;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class yw implements MembersInjector<ys> {
    static final /* synthetic */ boolean f11377a = (!yw.class.desiredAssertionStatus());
    private final Provider<qg> f11378b;
    private final Provider<b> f11379c;
    private final Provider<yk> f11380d;
    private final Provider<tj> f11381e;
    private final Provider<o> f11382f;
    private final Provider<zf> f11383g;

    public /* synthetic */ void injectMembers(Object obj) {
        m14179a((ys) obj);
    }

    public yw(Provider<qg> provider, Provider<b> provider2, Provider<yk> provider3, Provider<tj> provider4, Provider<o> provider5, Provider<zf> provider6) {
        if (f11377a || provider != null) {
            this.f11378b = provider;
            if (f11377a || provider2 != null) {
                this.f11379c = provider2;
                if (f11377a || provider3 != null) {
                    this.f11380d = provider3;
                    if (f11377a || provider4 != null) {
                        this.f11381e = provider4;
                        if (f11377a || provider5 != null) {
                            this.f11382f = provider5;
                            if (f11377a || provider6 != null) {
                                this.f11383g = provider6;
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

    public static MembersInjector<ys> m14178a(Provider<qg> provider, Provider<b> provider2, Provider<yk> provider3, Provider<tj> provider4, Provider<o> provider5, Provider<zf> provider6) {
        return new yw(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public void m14179a(ys ysVar) {
        if (ysVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ysVar.eventBus = (qg) this.f11378b.get();
        ysVar.d = (b) this.f11379c.get();
        ysVar.e = (yk) this.f11380d.get();
        ysVar.f = (tj) this.f11381e.get();
        ysVar.g = (o) this.f11382f.get();
        ysVar.h = (zf) this.f11383g.get();
    }
}
