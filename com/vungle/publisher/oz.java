package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class oz implements Factory<oy> {
    static final /* synthetic */ boolean f10900a = (!oz.class.desiredAssertionStatus());
    private final MembersInjector<oy> f10901b;

    public /* synthetic */ Object get() {
        return m13809a();
    }

    public oz(MembersInjector<oy> membersInjector) {
        if (f10900a || membersInjector != null) {
            this.f10901b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public oy m13809a() {
        return (oy) MembersInjectors.injectMembers(this.f10901b, new oy());
    }

    public static Factory<oy> m13808a(MembersInjector<oy> membersInjector) {
        return new oz(membersInjector);
    }
}
