package com.vungle.publisher;

import com.vungle.publisher.im.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class iq implements MembersInjector<im> {
    static final /* synthetic */ boolean f10479a = (!iq.class.desiredAssertionStatus());
    private final Provider<a> f10480b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13436a((im) obj);
    }

    public iq(Provider<a> provider) {
        if (f10479a || provider != null) {
            this.f10480b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<im> m13435a(Provider<a> provider) {
        return new iq(provider);
    }

    public void m13436a(im imVar) {
        if (imVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        imVar.a = (a) this.f10480b.get();
    }
}
