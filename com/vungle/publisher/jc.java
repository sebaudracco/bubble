package com.vungle.publisher;

import com.vungle.publisher.iz.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jc implements MembersInjector<a> {
    static final /* synthetic */ boolean f10517a = (!jc.class.desiredAssertionStatus());
    private final Provider<ci> f10518b;
    private final Provider<iz> f10519c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13466a((a) obj);
    }

    public jc(Provider<ci> provider, Provider<iz> provider2) {
        if (f10517a || provider != null) {
            this.f10518b = provider;
            if (f10517a || provider2 != null) {
                this.f10519c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13465a(Provider<ci> provider, Provider<iz> provider2) {
        return new jc(provider, provider2);
    }

    public void m13466a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10518b.get();
        aVar.a = this.f10519c;
    }
}
