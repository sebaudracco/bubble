package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class qq implements MembersInjector<qo> {
    static final /* synthetic */ boolean f10945a = (!qq.class.desiredAssertionStatus());
    private final Provider<String> f10946b;
    private final Provider<String> f10947c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13841a((qo) obj);
    }

    public qq(Provider<String> provider, Provider<String> provider2) {
        if (f10945a || provider != null) {
            this.f10946b = provider;
            if (f10945a || provider2 != null) {
                this.f10947c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<qo> m13840a(Provider<String> provider, Provider<String> provider2) {
        return new qq(provider, provider2);
    }

    public void m13841a(qo qoVar) {
        if (qoVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        qoVar.a = this.f10946b;
        qoVar.b = this.f10947c;
    }
}
