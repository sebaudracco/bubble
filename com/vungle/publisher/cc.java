package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class cc implements Factory<cb> {
    static final /* synthetic */ boolean f9831a = (!cc.class.desiredAssertionStatus());
    private final MembersInjector<cb> f9832b;

    public /* synthetic */ Object get() {
        return m12909a();
    }

    public cc(MembersInjector<cb> membersInjector) {
        if (f9831a || membersInjector != null) {
            this.f9832b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public cb m12909a() {
        return (cb) MembersInjectors.injectMembers(this.f9832b, new cb());
    }

    public static Factory<cb> m12908a(MembersInjector<cb> membersInjector) {
        return new cc(membersInjector);
    }
}
