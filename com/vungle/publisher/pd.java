package com.vungle.publisher;

import com.vungle.publisher.oy.a.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class pd implements MembersInjector<a> {
    static final /* synthetic */ boolean f10915a = (!pd.class.desiredAssertionStatus());
    private final Provider<oy.a> f10916b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13817a((a) obj);
    }

    public pd(Provider<oy.a> provider) {
        if (f10915a || provider != null) {
            this.f10916b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13816a(Provider<oy.a> provider) {
        return new pd(provider);
    }

    public void m13817a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (oy.a) this.f10916b.get();
    }
}
