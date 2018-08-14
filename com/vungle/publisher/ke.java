package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ke implements Factory<kd> {
    static final /* synthetic */ boolean f10612a = (!ke.class.desiredAssertionStatus());
    private final MembersInjector<kd> f10613b;

    public /* synthetic */ Object get() {
        return m13565a();
    }

    public ke(MembersInjector<kd> membersInjector) {
        if (f10612a || membersInjector != null) {
            this.f10613b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public kd m13565a() {
        return (kd) MembersInjectors.injectMembers(this.f10613b, new kd());
    }

    public static Factory<kd> m13564a(MembersInjector<kd> membersInjector) {
        return new ke(membersInjector);
    }
}
