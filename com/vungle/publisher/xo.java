package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class xo implements Factory<xg> {
    static final /* synthetic */ boolean f11301a = (!xo.class.desiredAssertionStatus());
    private final MembersInjector<xg> f11302b;

    public /* synthetic */ Object get() {
        return m14133a();
    }

    public xo(MembersInjector<xg> membersInjector) {
        if (f11301a || membersInjector != null) {
            this.f11302b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public xg m14133a() {
        return (xg) MembersInjectors.injectMembers(this.f11302b, new xg());
    }

    public static Factory<xg> m14132a(MembersInjector<xg> membersInjector) {
        return new xo(membersInjector);
    }
}
