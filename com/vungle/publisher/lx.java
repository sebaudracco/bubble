package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class lx implements Factory<lw> {
    static final /* synthetic */ boolean f10729a = (!lx.class.desiredAssertionStatus());
    private final MembersInjector<lw> f10730b;

    public /* synthetic */ Object get() {
        return m13669a();
    }

    public lx(MembersInjector<lw> membersInjector) {
        if (f10729a || membersInjector != null) {
            this.f10730b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public lw m13669a() {
        return (lw) MembersInjectors.injectMembers(this.f10730b, new lw());
    }

    public static Factory<lw> m13668a(MembersInjector<lw> membersInjector) {
        return new lx(membersInjector);
    }
}
