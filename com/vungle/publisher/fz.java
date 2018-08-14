package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class fz implements Factory<ek> {
    static final /* synthetic */ boolean f10166a = (!fz.class.desiredAssertionStatus());
    private final MembersInjector<ek> f10167b;

    public /* synthetic */ Object get() {
        return m13233a();
    }

    public fz(MembersInjector<ek> membersInjector) {
        if (f10166a || membersInjector != null) {
            this.f10167b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ek m13233a() {
        return (ek) MembersInjectors.injectMembers(this.f10167b, new ek());
    }

    public static Factory<ek> m13232a(MembersInjector<ek> membersInjector) {
        return new fz(membersInjector);
    }
}
