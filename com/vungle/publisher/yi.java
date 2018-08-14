package com.vungle.publisher;

import com.vungle.publisher.yg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class yi implements MembersInjector<a> {
    static final /* synthetic */ boolean f11344a = (!yi.class.desiredAssertionStatus());
    private final Provider<wg.a> f11345b;
    private final Provider<wj.a> f11346c;
    private final Provider<wr.a> f11347d;

    public /* synthetic */ void injectMembers(Object obj) {
        m14159a((a) obj);
    }

    public yi(Provider<wg.a> provider, Provider<wj.a> provider2, Provider<wr.a> provider3) {
        if (f11344a || provider != null) {
            this.f11345b = provider;
            if (f11344a || provider2 != null) {
                this.f11346c = provider2;
                if (f11344a || provider3 != null) {
                    this.f11347d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m14158a(Provider<wg.a> provider, Provider<wj.a> provider2, Provider<wr.a> provider3) {
        return new yi(provider, provider2, provider3);
    }

    public void m14159a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (wg.a) this.f11345b.get();
        aVar.b = (wj.a) this.f11346c.get();
        aVar.c = (wr.a) this.f11347d.get();
    }
}
