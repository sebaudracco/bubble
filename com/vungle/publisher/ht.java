package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ht implements Factory<hs> {
    static final /* synthetic */ boolean f10299a = (!ht.class.desiredAssertionStatus());
    private final MembersInjector<hs> f10300b;

    public /* synthetic */ Object get() {
        return m13326a();
    }

    public ht(MembersInjector<hs> membersInjector) {
        if (f10299a || membersInjector != null) {
            this.f10300b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public hs m13326a() {
        return (hs) MembersInjectors.injectMembers(this.f10300b, new hs());
    }

    public static Factory<hs> m13325a(MembersInjector<hs> membersInjector) {
        return new ht(membersInjector);
    }
}
