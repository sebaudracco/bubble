package com.vungle.publisher;

import com.vungle.publisher.fb.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fe implements MembersInjector<a> {
    static final /* synthetic */ boolean f10103a = (!fe.class.desiredAssertionStatus());
    private final Provider<ci> f10104b;
    private final Provider<fb> f10105c;
    private final Provider<fh.a> f10106d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13197a((a) obj);
    }

    public fe(Provider<ci> provider, Provider<fb> provider2, Provider<fh.a> provider3) {
        if (f10103a || provider != null) {
            this.f10104b = provider;
            if (f10103a || provider2 != null) {
                this.f10105c = provider2;
                if (f10103a || provider3 != null) {
                    this.f10106d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13196a(Provider<ci> provider, Provider<fb> provider2, Provider<fh.a> provider3) {
        return new fe(provider, provider2, provider3);
    }

    public void m13197a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f10104b.get();
        aVar.a = this.f10105c;
        aVar.b = (fh.a) this.f10106d.get();
    }
}
