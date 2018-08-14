package com.vungle.publisher;

import com.vungle.publisher.ek.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ga implements Factory<a> {
    static final /* synthetic */ boolean f10170a = (!ga.class.desiredAssertionStatus());
    private final MembersInjector<a> f10171b;

    public /* synthetic */ Object get() {
        return m13237a();
    }

    public ga(MembersInjector<a> membersInjector) {
        if (f10170a || membersInjector != null) {
            this.f10171b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13237a() {
        return (a) MembersInjectors.injectMembers(this.f10171b, new a());
    }

    public static Factory<a> m13236a(MembersInjector<a> membersInjector) {
        return new ga(membersInjector);
    }
}
