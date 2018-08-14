package com.vungle.publisher;

import com.vungle.publisher.fb.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ff implements MembersInjector<fb> {
    static final /* synthetic */ boolean f10107a = (!ff.class.desiredAssertionStatus());
    private final Provider<ci> f10108b;
    private final Provider<a> f10109c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13199a((fb) obj);
    }

    public ff(Provider<ci> provider, Provider<a> provider2) {
        if (f10107a || provider != null) {
            this.f10108b = provider;
            if (f10107a || provider2 != null) {
                this.f10109c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<fb> m13198a(Provider<ci> provider, Provider<a> provider2) {
        return new ff(provider, provider2);
    }

    public void m13199a(fb fbVar) {
        if (fbVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        fbVar.v = (ci) this.f10108b.get();
        fbVar.e = (a) this.f10109c.get();
    }
}
