package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class sa implements Factory<rz> {
    static final /* synthetic */ boolean f11044a = (!sa.class.desiredAssertionStatus());
    private final MembersInjector<rz> f11045b;

    public /* synthetic */ Object get() {
        return m13900a();
    }

    public sa(MembersInjector<rz> membersInjector) {
        if (f11044a || membersInjector != null) {
            this.f11045b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public rz m13900a() {
        return (rz) MembersInjectors.injectMembers(this.f11045b, new rz());
    }

    public static Factory<rz> m13899a(MembersInjector<rz> membersInjector) {
        return new sa(membersInjector);
    }
}
