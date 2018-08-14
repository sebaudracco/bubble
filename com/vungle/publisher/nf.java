package com.vungle.publisher;

import com.vungle.publisher.my.b.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class nf implements Factory<a> {
    static final /* synthetic */ boolean f10797a = (!nf.class.desiredAssertionStatus());
    private final MembersInjector<a> f10798b;

    public /* synthetic */ Object get() {
        return m13742a();
    }

    public nf(MembersInjector<a> membersInjector) {
        if (f10797a || membersInjector != null) {
            this.f10798b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13742a() {
        return (a) MembersInjectors.injectMembers(this.f10798b, new a());
    }

    public static Factory<a> m13741a(MembersInjector<a> membersInjector) {
        return new nf(membersInjector);
    }
}
