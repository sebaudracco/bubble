package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class dg implements Factory<df> {
    static final /* synthetic */ boolean f9936a = (!dg.class.desiredAssertionStatus());
    private final MembersInjector<df> f9937b;

    public /* synthetic */ Object get() {
        return m13045a();
    }

    public dg(MembersInjector<df> membersInjector) {
        if (f9936a || membersInjector != null) {
            this.f9937b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public df m13045a() {
        return (df) MembersInjectors.injectMembers(this.f9937b, new df());
    }

    public static Factory<df> m13044a(MembersInjector<df> membersInjector) {
        return new dg(membersInjector);
    }
}
