package com.vungle.publisher;

import com.vungle.publisher.env.C4179n;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cl implements MembersInjector<ci> {
    static final /* synthetic */ boolean f9847a = (!cl.class.desiredAssertionStatus());
    private final Provider<qo> f9848b;
    private final Provider<qg> f9849c;
    private final Provider<C4179n> f9850d;
    private final Provider<bw> f9851e;

    public /* synthetic */ void injectMembers(Object obj) {
        m12933a((ci) obj);
    }

    public cl(Provider<qo> provider, Provider<qg> provider2, Provider<C4179n> provider3, Provider<bw> provider4) {
        if (f9847a || provider != null) {
            this.f9848b = provider;
            if (f9847a || provider2 != null) {
                this.f9849c = provider2;
                if (f9847a || provider3 != null) {
                    this.f9850d = provider3;
                    if (f9847a || provider4 != null) {
                        this.f9851e = provider4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ci> m12932a(Provider<qo> provider, Provider<qg> provider2, Provider<C4179n> provider3, Provider<bw> provider4) {
        return new cl(provider, provider2, provider3, provider4);
    }

    public void m12933a(ci ciVar) {
        if (ciVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ciVar.a = (qo) this.f9848b.get();
        ciVar.b = (qg) this.f9849c.get();
        ciVar.c = (C4179n) this.f9850d.get();
        ciVar.d = (bw) this.f9851e.get();
    }
}
