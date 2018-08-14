package com.vungle.publisher;

import com.vungle.publisher.kd.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kh implements MembersInjector<kd> {
    static final /* synthetic */ boolean f10620a = (!kh.class.desiredAssertionStatus());
    private final Provider<ci> f10621b;
    private final Provider<a> f10622c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13571a((kd) obj);
    }

    public kh(Provider<ci> provider, Provider<a> provider2) {
        if (f10620a || provider != null) {
            this.f10621b = provider;
            if (f10620a || provider2 != null) {
                this.f10622c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<kd> m13570a(Provider<ci> provider, Provider<a> provider2) {
        return new kh(provider, provider2);
    }

    public void m13571a(kd kdVar) {
        if (kdVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        kdVar.v = (ci) this.f10621b.get();
        kdVar.e = (a) this.f10622c.get();
    }
}
