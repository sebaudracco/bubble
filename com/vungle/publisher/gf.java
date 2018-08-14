package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class gf implements Factory<ge> {
    static final /* synthetic */ boolean f10181a = (!gf.class.desiredAssertionStatus());
    private final MembersInjector<ge> f10182b;

    public /* synthetic */ Object get() {
        return m13253a();
    }

    public gf(MembersInjector<ge> membersInjector) {
        if (f10181a || membersInjector != null) {
            this.f10182b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ge m13253a() {
        return (ge) MembersInjectors.injectMembers(this.f10182b, new ge());
    }

    public static Factory<ge> m13252a(MembersInjector<ge> membersInjector) {
        return new gf(membersInjector);
    }
}
