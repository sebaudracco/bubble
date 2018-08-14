package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ii implements Factory<ic> {
    static final /* synthetic */ boolean f10332a = (!ii.class.desiredAssertionStatus());
    private final MembersInjector<ic> f10333b;

    public /* synthetic */ Object get() {
        return m13352a();
    }

    public ii(MembersInjector<ic> membersInjector) {
        if (f10332a || membersInjector != null) {
            this.f10333b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ic m13352a() {
        return (ic) MembersInjectors.injectMembers(this.f10333b, new ic());
    }

    public static Factory<ic> m13351a(MembersInjector<ic> membersInjector) {
        return new ii(membersInjector);
    }
}
