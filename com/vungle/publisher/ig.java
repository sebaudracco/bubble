package com.vungle.publisher;

import com.vungle.publisher.id.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ig implements MembersInjector<a> {
    static final /* synthetic */ boolean f10326a = (!ig.class.desiredAssertionStatus());
    private final Provider<ci> f10327b;
    private final Provider<id> f10328c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13348a((a) obj);
    }

    public ig(Provider<ci> provider, Provider<id> provider2) {
        if (f10326a || provider != null) {
            this.f10327b = provider;
            if (f10326a || provider2 != null) {
                this.f10328c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13347a(Provider<ci> provider, Provider<id> provider2) {
        return new ig(provider, provider2);
    }

    public void m13348a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10327b.get();
        aVar.a = this.f10328c;
    }
}
