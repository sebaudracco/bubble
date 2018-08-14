package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class xw implements Factory<xq> {
    static final /* synthetic */ boolean f11319a = (!xw.class.desiredAssertionStatus());
    private final MembersInjector<xq> f11320b;

    public /* synthetic */ Object get() {
        return m14137a();
    }

    public xw(MembersInjector<xq> membersInjector) {
        if (f11319a || membersInjector != null) {
            this.f11320b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public xq m14137a() {
        return (xq) MembersInjectors.injectMembers(this.f11320b, new xq());
    }

    public static Factory<xq> m14136a(MembersInjector<xq> membersInjector) {
        return new xw(membersInjector);
    }
}
