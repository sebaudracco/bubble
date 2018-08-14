package com.vungle.publisher;

import com.vungle.publisher.wg.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class wh implements Factory<a> {
    static final /* synthetic */ boolean f11229a = (!wh.class.desiredAssertionStatus());
    private final MembersInjector<a> f11230b;

    public /* synthetic */ Object get() {
        return m14062a();
    }

    public wh(MembersInjector<a> membersInjector) {
        if (f11229a || membersInjector != null) {
            this.f11230b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14062a() {
        return (a) MembersInjectors.injectMembers(this.f11230b, new a());
    }

    public static Factory<a> m14061a(MembersInjector<a> membersInjector) {
        return new wh(membersInjector);
    }
}
