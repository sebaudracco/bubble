package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class cu implements Factory<ct> {
    static final /* synthetic */ boolean f9893a = (!cu.class.desiredAssertionStatus());
    private final MembersInjector<ct> f9894b;

    public /* synthetic */ Object get() {
        return m12980a();
    }

    public cu(MembersInjector<ct> membersInjector) {
        if (f9893a || membersInjector != null) {
            this.f9894b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ct m12980a() {
        return (ct) MembersInjectors.injectMembers(this.f9894b, new ct());
    }

    public static Factory<ct> m12979a(MembersInjector<ct> membersInjector) {
        return new cu(membersInjector);
    }
}
