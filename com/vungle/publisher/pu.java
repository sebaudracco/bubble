package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class pu implements Factory<pj> {
    static final /* synthetic */ boolean f10919a = (!pu.class.desiredAssertionStatus());
    private final MembersInjector<pj> f10920b;

    public /* synthetic */ Object get() {
        return m13821a();
    }

    public pu(MembersInjector<pj> membersInjector) {
        if (f10919a || membersInjector != null) {
            this.f10920b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public pj m13821a() {
        return (pj) MembersInjectors.injectMembers(this.f10920b, new pj());
    }

    public static Factory<pj> m13820a(MembersInjector<pj> membersInjector) {
        return new pu(membersInjector);
    }
}
