package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class cg implements Factory<cf> {
    static final /* synthetic */ boolean f9838a = (!cg.class.desiredAssertionStatus());
    private final MembersInjector<cf> f9839b;

    public /* synthetic */ Object get() {
        return m12927a();
    }

    public cg(MembersInjector<cf> membersInjector) {
        if (f9838a || membersInjector != null) {
            this.f9839b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public cf m12927a() {
        return (cf) MembersInjectors.injectMembers(this.f9839b, new cf());
    }

    public static Factory<cf> m12926a(MembersInjector<cf> membersInjector) {
        return new cg(membersInjector);
    }
}
