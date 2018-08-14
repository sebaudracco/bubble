package com.vungle.publisher;

import com.vungle.publisher.fq.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fu implements MembersInjector<fq> {
    static final /* synthetic */ boolean f10144a = (!fu.class.desiredAssertionStatus());
    private final Provider<a> f10145b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13223a((fq) obj);
    }

    public fu(Provider<a> provider) {
        if (f10144a || provider != null) {
            this.f10145b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<fq> m13222a(Provider<a> provider) {
        return new fu(provider);
    }

    public void m13223a(fq fqVar) {
        if (fqVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        fqVar.a = (a) this.f10145b.get();
    }
}
