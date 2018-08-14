package com.vungle.publisher.log;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4232b implements Factory<a> {
    static final /* synthetic */ boolean f10705a = (!C4232b.class.desiredAssertionStatus());
    private final MembersInjector<a> f10706b;

    public /* synthetic */ Object get() {
        return m13651a();
    }

    public C4232b(MembersInjector<a> membersInjector) {
        if (f10705a || membersInjector != null) {
            this.f10706b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13651a() {
        return (a) MembersInjectors.injectMembers(this.f10706b, new a());
    }

    public static Factory<a> m13650a(MembersInjector<a> membersInjector) {
        return new C4232b(membersInjector);
    }
}
