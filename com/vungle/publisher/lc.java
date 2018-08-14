package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class lc implements Factory<lb> {
    static final /* synthetic */ boolean f10684a = (!lc.class.desiredAssertionStatus());
    private final MembersInjector<lb> f10685b;

    public /* synthetic */ Object get() {
        return m13607a();
    }

    public lc(MembersInjector<lb> membersInjector) {
        if (f10684a || membersInjector != null) {
            this.f10685b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public lb m13607a() {
        return (lb) MembersInjectors.injectMembers(this.f10685b, new lb());
    }

    public static Factory<lb> m13606a(MembersInjector<lb> membersInjector) {
        return new lc(membersInjector);
    }
}
