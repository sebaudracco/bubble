package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class hl implements Factory<hk> {
    static final /* synthetic */ boolean f10273a = (!hl.class.desiredAssertionStatus());
    private final MembersInjector<hk> f10274b;

    public /* synthetic */ Object get() {
        return m13304a();
    }

    public hl(MembersInjector<hk> membersInjector) {
        if (f10273a || membersInjector != null) {
            this.f10274b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public hk m13304a() {
        return (hk) MembersInjectors.injectMembers(this.f10274b, new hk());
    }

    public static Factory<hk> m13303a(MembersInjector<hk> membersInjector) {
        return new hl(membersInjector);
    }
}
