package com.vungle.publisher;

import com.vungle.publisher.gl.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class go implements MembersInjector<a> {
    static final /* synthetic */ boolean f10194a = (!go.class.desiredAssertionStatus());
    private final Provider<gl> f10195b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13265a((a) obj);
    }

    public go(Provider<gl> provider) {
        if (f10194a || provider != null) {
            this.f10195b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13264a(Provider<gl> provider) {
        return new go(provider);
    }

    public void m13265a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (gl) this.f10195b.get();
    }
}
