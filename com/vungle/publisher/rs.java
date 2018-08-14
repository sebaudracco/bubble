package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class rs implements Factory<rr> {
    static final /* synthetic */ boolean f10999a = (!rs.class.desiredAssertionStatus());
    private final MembersInjector<rr> f11000b;

    public /* synthetic */ Object get() {
        return m13890a();
    }

    public rs(MembersInjector<rr> membersInjector) {
        if (f10999a || membersInjector != null) {
            this.f11000b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public rr m13890a() {
        return (rr) MembersInjectors.injectMembers(this.f11000b, new rr());
    }

    public static Factory<rr> m13889a(MembersInjector<rr> membersInjector) {
        return new rs(membersInjector);
    }
}
