package com.vungle.publisher;

import com.vungle.publisher.ns.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class nw implements Factory<a> {
    static final /* synthetic */ boolean f10832a = (!nw.class.desiredAssertionStatus());
    private final MembersInjector<a> f10833b;

    public /* synthetic */ Object get() {
        return m13764a();
    }

    public nw(MembersInjector<a> membersInjector) {
        if (f10832a || membersInjector != null) {
            this.f10833b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13764a() {
        return (a) MembersInjectors.injectMembers(this.f10833b, new a());
    }

    public static Factory<a> m13763a(MembersInjector<a> membersInjector) {
        return new nw(membersInjector);
    }
}
