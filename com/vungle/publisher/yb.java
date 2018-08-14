package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class yb implements Factory<xy> {
    static final /* synthetic */ boolean f11327a = (!yb.class.desiredAssertionStatus());
    private final MembersInjector<xy> f11328b;

    public /* synthetic */ Object get() {
        return m14141a();
    }

    public yb(MembersInjector<xy> membersInjector) {
        if (f11327a || membersInjector != null) {
            this.f11328b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public xy m14141a() {
        return (xy) MembersInjectors.injectMembers(this.f11328b, new xy());
    }

    public static Factory<xy> m14140a(MembersInjector<xy> membersInjector) {
        return new yb(membersInjector);
    }
}
