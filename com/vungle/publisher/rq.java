package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.ro.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rq implements MembersInjector<a> {
    static final /* synthetic */ boolean f10995a = (!rq.class.desiredAssertionStatus());
    private final Provider<Context> f10996b;
    private final Provider<mv> f10997c;
    private final Provider<zo> f10998d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13888a((a) obj);
    }

    public rq(Provider<Context> provider, Provider<mv> provider2, Provider<zo> provider3) {
        if (f10995a || provider != null) {
            this.f10996b = provider;
            if (f10995a || provider2 != null) {
                this.f10997c = provider2;
                if (f10995a || provider3 != null) {
                    this.f10998d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13887a(Provider<Context> provider, Provider<mv> provider2, Provider<zo> provider3) {
        return new rq(provider, provider2, provider3);
    }

    public void m13888a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (Context) this.f10996b.get();
        aVar.b = (mv) this.f10997c.get();
        aVar.c = (zo) this.f10998d.get();
    }
}
