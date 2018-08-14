package com.vungle.publisher;

import com.vungle.publisher.ds.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class du implements Factory<a> {
    static final /* synthetic */ boolean f9962a = (!du.class.desiredAssertionStatus());
    private final MembersInjector<a> f9963b;

    public /* synthetic */ Object get() {
        return m13072a();
    }

    public du(MembersInjector<a> membersInjector) {
        if (f9962a || membersInjector != null) {
            this.f9963b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13072a() {
        return (a) MembersInjectors.injectMembers(this.f9963b, new a());
    }

    public static Factory<a> m13071a(MembersInjector<a> membersInjector) {
        return new du(membersInjector);
    }
}
