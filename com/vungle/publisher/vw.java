package com.vungle.publisher;

import com.vungle.publisher.vu.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class vw implements Factory<a> {
    static final /* synthetic */ boolean f11189a = (!vw.class.desiredAssertionStatus());
    private final MembersInjector<a> f11190b;

    public /* synthetic */ Object get() {
        return m14013a();
    }

    public vw(MembersInjector<a> membersInjector) {
        if (f11189a || membersInjector != null) {
            this.f11190b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14013a() {
        return (a) MembersInjectors.injectMembers(this.f11190b, new a());
    }

    public static Factory<a> m14012a(MembersInjector<a> membersInjector) {
        return new vw(membersInjector);
    }
}
