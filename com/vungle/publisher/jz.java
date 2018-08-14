package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class jz implements Factory<jy> {
    static final /* synthetic */ boolean f10600a = (!jz.class.desiredAssertionStatus());
    private final MembersInjector<jy> f10601b;

    public /* synthetic */ Object get() {
        return m13555a();
    }

    public jz(MembersInjector<jy> membersInjector) {
        if (f10600a || membersInjector != null) {
            this.f10601b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public jy m13555a() {
        return (jy) MembersInjectors.injectMembers(this.f10601b, new jy());
    }

    public static Factory<jy> m13554a(MembersInjector<jy> membersInjector) {
        return new jz(membersInjector);
    }
}
