package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class qb implements Factory<py> {
    static final /* synthetic */ boolean f10931a = (!qb.class.desiredAssertionStatus());
    private final MembersInjector<py> f10932b;

    public /* synthetic */ Object get() {
        return m13829a();
    }

    public qb(MembersInjector<py> membersInjector) {
        if (f10931a || membersInjector != null) {
            this.f10932b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public py m13829a() {
        return (py) MembersInjectors.injectMembers(this.f10932b, new py());
    }

    public static Factory<py> m13828a(MembersInjector<py> membersInjector) {
        return new qb(membersInjector);
    }
}
