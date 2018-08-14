package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ie implements Factory<id> {
    static final /* synthetic */ boolean f10322a = (!ie.class.desiredAssertionStatus());
    private final MembersInjector<id> f10323b;

    public /* synthetic */ Object get() {
        return m13344a();
    }

    public ie(MembersInjector<id> membersInjector) {
        if (f10322a || membersInjector != null) {
            this.f10323b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public id m13344a() {
        return (id) MembersInjectors.injectMembers(this.f10323b, new id());
    }

    public static Factory<id> m13343a(MembersInjector<id> membersInjector) {
        return new ie(membersInjector);
    }
}
