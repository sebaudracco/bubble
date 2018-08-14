package com.vungle.publisher;

import com.vungle.publisher.wd.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class we implements Factory<a> {
    static final /* synthetic */ boolean f11213a = (!we.class.desiredAssertionStatus());
    private final MembersInjector<a> f11214b;

    public /* synthetic */ Object get() {
        return m14046a();
    }

    public we(MembersInjector<a> membersInjector) {
        if (f11213a || membersInjector != null) {
            this.f11214b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14046a() {
        return (a) MembersInjectors.injectMembers(this.f11214b, new a());
    }

    public static Factory<a> m14045a(MembersInjector<a> membersInjector) {
        return new we(membersInjector);
    }
}
