package com.vungle.publisher;

import com.vungle.publisher.iz.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jd implements MembersInjector<iz> {
    static final /* synthetic */ boolean f10520a = (!jd.class.desiredAssertionStatus());
    private final Provider<ci> f10521b;
    private final Provider<a> f10522c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13468a((iz) obj);
    }

    public jd(Provider<ci> provider, Provider<a> provider2) {
        if (f10520a || provider != null) {
            this.f10521b = provider;
            if (f10520a || provider2 != null) {
                this.f10522c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<iz> m13467a(Provider<ci> provider, Provider<a> provider2) {
        return new jd(provider, provider2);
    }

    public void m13468a(iz izVar) {
        if (izVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        izVar.v = (ci) this.f10521b.get();
        izVar.a = (a) this.f10522c.get();
    }
}
