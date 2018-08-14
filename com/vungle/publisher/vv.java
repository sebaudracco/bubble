package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class vv implements Factory<vu> {
    static final /* synthetic */ boolean f11187a = (!vv.class.desiredAssertionStatus());
    private final MembersInjector<vu> f11188b;

    public /* synthetic */ Object get() {
        return m14011a();
    }

    public vv(MembersInjector<vu> membersInjector) {
        if (f11187a || membersInjector != null) {
            this.f11188b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public vu m14011a() {
        return (vu) MembersInjectors.injectMembers(this.f11188b, new vu());
    }

    public static Factory<vu> m14010a(MembersInjector<vu> membersInjector) {
        return new vv(membersInjector);
    }
}
