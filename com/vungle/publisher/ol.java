package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.oj.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ol implements MembersInjector<a> {
    static final /* synthetic */ boolean f10868a = (!ol.class.desiredAssertionStatus());
    private final Provider<Context> f10869b;
    private final Provider<mv> f10870c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13791a((a) obj);
    }

    public ol(Provider<Context> provider, Provider<mv> provider2) {
        if (f10868a || provider != null) {
            this.f10869b = provider;
            if (f10868a || provider2 != null) {
                this.f10870c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13790a(Provider<Context> provider, Provider<mv> provider2) {
        return new ol(provider, provider2);
    }

    public void m13791a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (Context) this.f10869b.get();
        aVar.b = (mv) this.f10870c.get();
    }
}
