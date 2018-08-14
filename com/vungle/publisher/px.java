package com.vungle.publisher;

import com.vungle.publisher.env.r;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class px implements MembersInjector<pj> {
    static final /* synthetic */ boolean f10925a = (!px.class.desiredAssertionStatus());
    private final Provider<qg> f10926b;
    private final Provider<bw> f10927c;
    private final Provider<c> f10928d;
    private final Provider<r> f10929e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13827a((pj) obj);
    }

    public px(Provider<qg> provider, Provider<bw> provider2, Provider<c> provider3, Provider<r> provider4) {
        if (f10925a || provider != null) {
            this.f10926b = provider;
            if (f10925a || provider2 != null) {
                this.f10927c = provider2;
                if (f10925a || provider3 != null) {
                    this.f10928d = provider3;
                    if (f10925a || provider4 != null) {
                        this.f10929e = provider4;
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

    public static MembersInjector<pj> m13826a(Provider<qg> provider, Provider<bw> provider2, Provider<c> provider3, Provider<r> provider4) {
        return new px(provider, provider2, provider3, provider4);
    }

    public void m13827a(pj pjVar) {
        if (pjVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        pjVar.eventBus = (qg) this.f10926b.get();
        pjVar.b = (bw) this.f10927c.get();
        pjVar.c = (c) this.f10928d.get();
        pjVar.d = (r) this.f10929e.get();
    }
}
