package com.vungle.publisher;

import com.vungle.publisher.ks.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kv implements MembersInjector<a> {
    static final /* synthetic */ boolean f10653a = (!kv.class.desiredAssertionStatus());
    private final Provider<ks> f10654b;
    private final Provider<jy.a> f10655c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13593a((a) obj);
    }

    public kv(Provider<ks> provider, Provider<jy.a> provider2) {
        if (f10653a || provider != null) {
            this.f10654b = provider;
            if (f10653a || provider2 != null) {
                this.f10655c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13592a(Provider<ks> provider, Provider<jy.a> provider2) {
        return new kv(provider, provider2);
    }

    public void m13593a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10654b;
        aVar.b = (jy.a) this.f10655c.get();
    }
}
