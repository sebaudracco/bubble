package com.vungle.publisher.env;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4175b implements Factory<a> {
    static final /* synthetic */ boolean f10013a = (!C4175b.class.desiredAssertionStatus());
    private final MembersInjector<a> f10014b;

    public /* synthetic */ Object get() {
        return m13130a();
    }

    public C4175b(MembersInjector<a> membersInjector) {
        if (f10013a || membersInjector != null) {
            this.f10014b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13130a() {
        return (a) MembersInjectors.injectMembers(this.f10014b, new a());
    }

    public static Factory<a> m13129a(MembersInjector<a> membersInjector) {
        return new C4175b(membersInjector);
    }
}
