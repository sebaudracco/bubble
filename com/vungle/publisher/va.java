package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class va implements Factory<uz> {
    static final /* synthetic */ boolean f11169a = (!va.class.desiredAssertionStatus());
    private final MembersInjector<uz> f11170b;

    public /* synthetic */ Object get() {
        return m14003a();
    }

    public va(MembersInjector<uz> membersInjector) {
        if (f11169a || membersInjector != null) {
            this.f11170b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public uz m14003a() {
        return (uz) MembersInjectors.injectMembers(this.f11170b, new uz());
    }

    public static Factory<uz> m14002a(MembersInjector<uz> membersInjector) {
        return new va(membersInjector);
    }
}
