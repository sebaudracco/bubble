package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class zg implements Factory<zf> {
    static final /* synthetic */ boolean f11386a = (!zg.class.desiredAssertionStatus());
    private final MembersInjector<zf> f11387b;

    public /* synthetic */ Object get() {
        return m14200a();
    }

    public zg(MembersInjector<zf> membersInjector) {
        if (f11386a || membersInjector != null) {
            this.f11387b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public zf m14200a() {
        return (zf) MembersInjectors.injectMembers(this.f11387b, new zf());
    }

    public static Factory<zf> m14199a(MembersInjector<zf> membersInjector) {
        return new zg(membersInjector);
    }
}
