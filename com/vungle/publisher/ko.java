package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ko implements Factory<ki> {
    static final /* synthetic */ boolean f10633a = (!ko.class.desiredAssertionStatus());
    private final MembersInjector<ki> f10634b;

    public /* synthetic */ Object get() {
        return m13581a();
    }

    public ko(MembersInjector<ki> membersInjector) {
        if (f10633a || membersInjector != null) {
            this.f10634b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ki m13581a() {
        return (ki) MembersInjectors.injectMembers(this.f10634b, new ki());
    }

    public static Factory<ki> m13580a(MembersInjector<ki> membersInjector) {
        return new ko(membersInjector);
    }
}
