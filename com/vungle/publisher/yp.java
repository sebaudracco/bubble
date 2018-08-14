package com.vungle.publisher;

import com.vungle.publisher.yn.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class yp implements Factory<a> {
    static final /* synthetic */ boolean f11359a = (!yp.class.desiredAssertionStatus());
    private final MembersInjector<a> f11360b;

    public /* synthetic */ Object get() {
        return m14167a();
    }

    public yp(MembersInjector<a> membersInjector) {
        if (f11359a || membersInjector != null) {
            this.f11360b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14167a() {
        return (a) MembersInjectors.injectMembers(this.f11360b, new a());
    }

    public static Factory<a> m14166a(MembersInjector<a> membersInjector) {
        return new yp(membersInjector);
    }
}
