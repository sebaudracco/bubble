package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class fi implements Factory<fh> {
    static final /* synthetic */ boolean f10110a = (!fi.class.desiredAssertionStatus());
    private final MembersInjector<fh> f10111b;

    public /* synthetic */ Object get() {
        return m13201a();
    }

    public fi(MembersInjector<fh> membersInjector) {
        if (f10110a || membersInjector != null) {
            this.f10111b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public fh m13201a() {
        return (fh) MembersInjectors.injectMembers(this.f10111b, new fh());
    }

    public static Factory<fh> m13200a(MembersInjector<fh> membersInjector) {
        return new fi(membersInjector);
    }
}
