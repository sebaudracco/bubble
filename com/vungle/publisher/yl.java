package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class yl implements Factory<yk> {
    static final /* synthetic */ boolean f11348a = (!yl.class.desiredAssertionStatus());
    private final MembersInjector<yk> f11349b;

    public /* synthetic */ Object get() {
        return m14161a();
    }

    public yl(MembersInjector<yk> membersInjector) {
        if (f11348a || membersInjector != null) {
            this.f11349b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public yk m14161a() {
        return (yk) MembersInjectors.injectMembers(this.f11349b, new yk());
    }

    public static Factory<yk> m14160a(MembersInjector<yk> membersInjector) {
        return new yl(membersInjector);
    }
}
