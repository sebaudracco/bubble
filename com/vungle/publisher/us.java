package com.vungle.publisher;

import com.vungle.publisher.uo.a;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

/* compiled from: vungle */
public final class us implements MembersInjector<uq> {
    static final /* synthetic */ boolean f11155a = (!us.class.desiredAssertionStatus());
    private final Provider<ub> f11156b;
    private final Provider<a> f11157c;
    private final Provider<te> f11158d;
    private final Provider<tn> f11159e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13992a((uq) obj);
    }

    public us(Provider<ub> provider, Provider<a> provider2, Provider<te> provider3, Provider<tn> provider4) {
        if (f11155a || provider != null) {
            this.f11156b = provider;
            if (f11155a || provider2 != null) {
                this.f11157c = provider2;
                if (f11155a || provider3 != null) {
                    this.f11158d = provider3;
                    if (f11155a || provider4 != null) {
                        this.f11159e = provider4;
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

    public static MembersInjector<uq> m13991a(Provider<ub> provider, Provider<a> provider2, Provider<te> provider3, Provider<tn> provider4) {
        return new us(provider, provider2, provider3, provider4);
    }

    public void m13992a(uq uqVar) {
        if (uqVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        uqVar.a = this.f11156b;
        uqVar.b = (a) this.f11157c.get();
        uqVar.c = DoubleCheck.lazy(this.f11158d);
        uqVar.d = DoubleCheck.lazy(this.f11159e);
    }
}
