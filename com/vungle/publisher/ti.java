package com.vungle.publisher;

import com.vungle.publisher.th.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ti implements Factory<a> {
    static final /* synthetic */ boolean f11092a = (!ti.class.desiredAssertionStatus());
    private final MembersInjector<a> f11093b;

    public /* synthetic */ Object get() {
        return m13944a();
    }

    public ti(MembersInjector<a> membersInjector) {
        if (f11092a || membersInjector != null) {
            this.f11093b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13944a() {
        return (a) MembersInjectors.injectMembers(this.f11093b, new a());
    }

    public static Factory<a> m13943a(MembersInjector<a> membersInjector) {
        return new ti(membersInjector);
    }
}
