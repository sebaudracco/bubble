package com.vungle.publisher;

import com.vungle.publisher.wz.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class xa implements Factory<a> {
    static final /* synthetic */ boolean f11290a = (!xa.class.desiredAssertionStatus());
    private final MembersInjector<a> f11291b;

    public /* synthetic */ Object get() {
        return m14123a();
    }

    public xa(MembersInjector<a> membersInjector) {
        if (f11290a || membersInjector != null) {
            this.f11291b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14123a() {
        return (a) MembersInjectors.injectMembers(this.f11291b, new a());
    }

    public static Factory<a> m14122a(MembersInjector<a> membersInjector) {
        return new xa(membersInjector);
    }
}
