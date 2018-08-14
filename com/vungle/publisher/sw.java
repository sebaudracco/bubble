package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class sw implements Factory<sv> {
    static final /* synthetic */ boolean f11063a = (!sw.class.desiredAssertionStatus());
    private final MembersInjector<sv> f11064b;

    public /* synthetic */ Object get() {
        return m13916a();
    }

    public sw(MembersInjector<sv> membersInjector) {
        if (f11063a || membersInjector != null) {
            this.f11064b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public sv m13916a() {
        return (sv) MembersInjectors.injectMembers(this.f11064b, new sv());
    }

    public static Factory<sv> m13915a(MembersInjector<sv> membersInjector) {
        return new sw(membersInjector);
    }
}
