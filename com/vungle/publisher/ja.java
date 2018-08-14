package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ja implements Factory<iz> {
    static final /* synthetic */ boolean f10513a = (!ja.class.desiredAssertionStatus());
    private final MembersInjector<iz> f10514b;

    public /* synthetic */ Object get() {
        return m13462a();
    }

    public ja(MembersInjector<iz> membersInjector) {
        if (f10513a || membersInjector != null) {
            this.f10514b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public iz m13462a() {
        return (iz) MembersInjectors.injectMembers(this.f10514b, new iz());
    }

    public static Factory<iz> m13461a(MembersInjector<iz> membersInjector) {
        return new ja(membersInjector);
    }
}
