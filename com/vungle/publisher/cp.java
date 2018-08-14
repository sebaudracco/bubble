package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class cp implements Factory<co> {
    static final /* synthetic */ boolean f9883a = (!cp.class.desiredAssertionStatus());
    private final MembersInjector<co> f9884b;

    public /* synthetic */ Object get() {
        return m12972a();
    }

    public cp(MembersInjector<co> membersInjector) {
        if (f9883a || membersInjector != null) {
            this.f9884b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public co m12972a() {
        return (co) MembersInjectors.injectMembers(this.f9884b, new co());
    }

    public static Factory<co> m12971a(MembersInjector<co> membersInjector) {
        return new cp(membersInjector);
    }
}
