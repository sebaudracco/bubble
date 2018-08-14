package com.vungle.publisher;

import com.vungle.publisher.wp.a.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class wq implements Factory<a> {
    static final /* synthetic */ boolean f11252a = (!wq.class.desiredAssertionStatus());
    private final MembersInjector<a> f11253b;

    public /* synthetic */ Object get() {
        return m14084a();
    }

    public wq(MembersInjector<a> membersInjector) {
        if (f11252a || membersInjector != null) {
            this.f11253b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14084a() {
        return (a) MembersInjectors.injectMembers(this.f11253b, new a());
    }

    public static Factory<a> m14083a(MembersInjector<a> membersInjector) {
        return new wq(membersInjector);
    }
}
