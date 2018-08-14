package com.vungle.publisher;

import com.vungle.publisher.hq.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ix implements MembersInjector<a> {
    static final /* synthetic */ boolean f10501a = (!ix.class.desiredAssertionStatus());
    private final Provider<ci> f10502b;
    private final Provider<C4238m.a> f10503c;
    private final Provider<hq> f10504d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13450a((a) obj);
    }

    public ix(Provider<ci> provider, Provider<C4238m.a> provider2, Provider<hq> provider3) {
        if (f10501a || provider != null) {
            this.f10502b = provider;
            if (f10501a || provider2 != null) {
                this.f10503c = provider2;
                if (f10501a || provider3 != null) {
                    this.f10504d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13449a(Provider<ci> provider, Provider<C4238m.a> provider2, Provider<hq> provider3) {
        return new ix(provider, provider2, provider3);
    }

    public void m13450a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10502b.get();
        aVar.e = (C4238m.a) this.f10503c.get();
        aVar.a = this.f10504d;
    }
}
