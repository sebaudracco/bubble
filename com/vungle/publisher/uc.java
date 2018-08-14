package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class uc implements Factory<ub> {
    static final /* synthetic */ boolean f11131a = (!uc.class.desiredAssertionStatus());
    private final MembersInjector<ub> f11132b;

    public /* synthetic */ Object get() {
        return m13968a();
    }

    public uc(MembersInjector<ub> membersInjector) {
        if (f11131a || membersInjector != null) {
            this.f11132b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ub m13968a() {
        return (ub) MembersInjectors.injectMembers(this.f11132b, new ub());
    }

    public static Factory<ub> m13967a(MembersInjector<ub> membersInjector) {
        return new uc(membersInjector);
    }
}
