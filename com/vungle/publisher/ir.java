package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ir implements Factory<hr> {
    static final /* synthetic */ boolean f10481a = (!ir.class.desiredAssertionStatus());
    private final MembersInjector<hr> f10482b;

    public /* synthetic */ Object get() {
        return m13438a();
    }

    public ir(MembersInjector<hr> membersInjector) {
        if (f10481a || membersInjector != null) {
            this.f10482b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public hr m13438a() {
        return (hr) MembersInjectors.injectMembers(this.f10482b, new hr());
    }

    public static Factory<hr> m13437a(MembersInjector<hr> membersInjector) {
        return new ir(membersInjector);
    }
}
