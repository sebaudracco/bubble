package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class gx implements Factory<gw> {
    static final /* synthetic */ boolean f10213a = (!gx.class.desiredAssertionStatus());
    private final MembersInjector<gw> f10214b;

    public /* synthetic */ Object get() {
        return m13277a();
    }

    public gx(MembersInjector<gw> membersInjector) {
        if (f10213a || membersInjector != null) {
            this.f10214b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public gw m13277a() {
        return (gw) MembersInjectors.injectMembers(this.f10214b, new gw());
    }

    public static Factory<gw> m13276a(MembersInjector<gw> membersInjector) {
        return new gx(membersInjector);
    }
}
