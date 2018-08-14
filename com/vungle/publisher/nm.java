package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class nm implements Factory<nk> {
    static final /* synthetic */ boolean f10803a = (!nm.class.desiredAssertionStatus());
    private final MembersInjector<nk> f10804b;

    public /* synthetic */ Object get() {
        return m13748a();
    }

    public nm(MembersInjector<nk> membersInjector) {
        if (f10803a || membersInjector != null) {
            this.f10804b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public nk m13748a() {
        return (nk) MembersInjectors.injectMembers(this.f10804b, new nk());
    }

    public static Factory<nk> m13747a(MembersInjector<nk> membersInjector) {
        return new nm(membersInjector);
    }
}
