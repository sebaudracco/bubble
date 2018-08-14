package com.vungle.publisher;

import com.vungle.publisher.wm.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class wn implements Factory<a> {
    static final /* synthetic */ boolean f11241a = (!wn.class.desiredAssertionStatus());
    private final MembersInjector<a> f11242b;

    public /* synthetic */ Object get() {
        return m14073a();
    }

    public wn(MembersInjector<a> membersInjector) {
        if (f11241a || membersInjector != null) {
            this.f11242b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14073a() {
        return (a) MembersInjectors.injectMembers(this.f11242b, new a());
    }

    public static Factory<a> m14072a(MembersInjector<a> membersInjector) {
        return new wn(membersInjector);
    }
}
