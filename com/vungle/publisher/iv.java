package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class iv implements Factory<hq> {
    static final /* synthetic */ boolean f10497a = (!iv.class.desiredAssertionStatus());
    private final MembersInjector<hq> f10498b;

    public /* synthetic */ Object get() {
        return m13446a();
    }

    public iv(MembersInjector<hq> membersInjector) {
        if (f10497a || membersInjector != null) {
            this.f10498b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public hq m13446a() {
        return (hq) MembersInjectors.injectMembers(this.f10498b, new hq());
    }

    public static Factory<hq> m13445a(MembersInjector<hq> membersInjector) {
        return new iv(membersInjector);
    }
}
