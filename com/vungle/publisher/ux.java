package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ux implements Factory<uw> {
    static final /* synthetic */ boolean f11163a = (!ux.class.desiredAssertionStatus());
    private final MembersInjector<uw> f11164b;

    public /* synthetic */ Object get() {
        return m13997a();
    }

    public ux(MembersInjector<uw> membersInjector) {
        if (f11163a || membersInjector != null) {
            this.f11164b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public uw m13997a() {
        return (uw) MembersInjectors.injectMembers(this.f11164b, new uw());
    }

    public static Factory<uw> m13996a(MembersInjector<uw> membersInjector) {
        return new ux(membersInjector);
    }
}
