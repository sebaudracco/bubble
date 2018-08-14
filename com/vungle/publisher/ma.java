package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ma implements Factory<lz> {
    static final /* synthetic */ boolean f10738a = (!ma.class.desiredAssertionStatus());
    private final MembersInjector<lz> f10739b;

    public /* synthetic */ Object get() {
        return m13673a();
    }

    public ma(MembersInjector<lz> membersInjector) {
        if (f10738a || membersInjector != null) {
            this.f10739b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public lz m13673a() {
        return (lz) MembersInjectors.injectMembers(this.f10739b, new lz());
    }

    public static Factory<lz> m13672a(MembersInjector<lz> membersInjector) {
        return new ma(membersInjector);
    }
}
