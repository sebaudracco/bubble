package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class jp implements Factory<jo> {
    static final /* synthetic */ boolean f10573a = (!jp.class.desiredAssertionStatus());
    private final MembersInjector<jo> f10574b;

    public /* synthetic */ Object get() {
        return m13539a();
    }

    public jp(MembersInjector<jo> membersInjector) {
        if (f10573a || membersInjector != null) {
            this.f10574b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public jo m13539a() {
        return (jo) MembersInjectors.injectMembers(this.f10574b, new jo());
    }

    public static Factory<jo> m13538a(MembersInjector<jo> membersInjector) {
        return new jp(membersInjector);
    }
}
