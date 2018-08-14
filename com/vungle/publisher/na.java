package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class na implements Factory<my> {
    static final /* synthetic */ boolean f10778a = (!na.class.desiredAssertionStatus());
    private final MembersInjector<my> f10779b;

    public /* synthetic */ Object get() {
        return m13732a();
    }

    public na(MembersInjector<my> membersInjector) {
        if (f10778a || membersInjector != null) {
            this.f10779b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public my m13732a() {
        return (my) MembersInjectors.injectMembers(this.f10779b, new my());
    }

    public static Factory<my> m13731a(MembersInjector<my> membersInjector) {
        return new na(membersInjector);
    }
}
