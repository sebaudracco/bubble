package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class yo implements Factory<yn> {
    static final /* synthetic */ boolean f11357a = (!yo.class.desiredAssertionStatus());
    private final MembersInjector<yn> f11358b;

    public /* synthetic */ Object get() {
        return m14165a();
    }

    public yo(MembersInjector<yn> membersInjector) {
        if (f11357a || membersInjector != null) {
            this.f11358b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public yn m14165a() {
        return (yn) MembersInjectors.injectMembers(this.f11358b, new yn());
    }

    public static Factory<yn> m14164a(MembersInjector<yn> membersInjector) {
        return new yo(membersInjector);
    }
}
