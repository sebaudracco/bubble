package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class nu implements Factory<ns> {
    static final /* synthetic */ boolean f10821a = (!nu.class.desiredAssertionStatus());
    private final MembersInjector<ns> f10822b;

    public /* synthetic */ Object get() {
        return m13760a();
    }

    public nu(MembersInjector<ns> membersInjector) {
        if (f10821a || membersInjector != null) {
            this.f10822b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ns m13760a() {
        return (ns) MembersInjectors.injectMembers(this.f10822b, new ns());
    }

    public static Factory<ns> m13759a(MembersInjector<ns> membersInjector) {
        return new nu(membersInjector);
    }
}
