package com.vungle.publisher;

import com.vungle.publisher.op.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ow implements MembersInjector<a> {
    static final /* synthetic */ boolean f10885a = (!ow.class.desiredAssertionStatus());
    private final Provider<op> f10886b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13805a((a) obj);
    }

    public ow(Provider<op> provider) {
        if (f10885a || provider != null) {
            this.f10886b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13804a(Provider<op> provider) {
        return new ow(provider);
    }

    public void m13805a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.f10886b;
    }
}
