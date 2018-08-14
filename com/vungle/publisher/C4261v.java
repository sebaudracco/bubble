package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4261v implements Factory<u> {
    static final /* synthetic */ boolean f11167a = (!C4261v.class.desiredAssertionStatus());
    private final MembersInjector<u> f11168b;

    public /* synthetic */ Object get() {
        return m14001a();
    }

    public C4261v(MembersInjector<u> membersInjector) {
        if (f11167a || membersInjector != null) {
            this.f11168b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public u m14001a() {
        return (u) MembersInjectors.injectMembers(this.f11168b, new u());
    }

    public static Factory<u> m14000a(MembersInjector<u> membersInjector) {
        return new C4261v(membersInjector);
    }
}
