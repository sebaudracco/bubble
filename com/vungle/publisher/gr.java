package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class gr implements Factory<gq> {
    static final /* synthetic */ boolean f10202a = (!gr.class.desiredAssertionStatus());
    private final MembersInjector<gq> f10203b;

    public /* synthetic */ Object get() {
        return m13269a();
    }

    public gr(MembersInjector<gq> membersInjector) {
        if (f10202a || membersInjector != null) {
            this.f10203b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public gq m13269a() {
        return (gq) MembersInjectors.injectMembers(this.f10203b, new gq());
    }

    public static Factory<gq> m13268a(MembersInjector<gq> membersInjector) {
        return new gr(membersInjector);
    }
}
