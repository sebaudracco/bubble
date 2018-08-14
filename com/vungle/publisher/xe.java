package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class xe implements Factory<xd> {
    static final /* synthetic */ boolean f11296a = (!xe.class.desiredAssertionStatus());
    private final MembersInjector<xd> f11297b;

    public /* synthetic */ Object get() {
        return m14129a();
    }

    public xe(MembersInjector<xd> membersInjector) {
        if (f11296a || membersInjector != null) {
            this.f11297b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public xd m14129a() {
        return (xd) MembersInjectors.injectMembers(this.f11297b, new xd());
    }

    public static Factory<xd> m14128a(MembersInjector<xd> membersInjector) {
        return new xe(membersInjector);
    }
}
