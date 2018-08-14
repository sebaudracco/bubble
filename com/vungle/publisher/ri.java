package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ri implements Factory<rg> {
    static final /* synthetic */ boolean f10965a = (!ri.class.desiredAssertionStatus());
    private final MembersInjector<rg> f10966b;

    public /* synthetic */ Object get() {
        return m13873a();
    }

    public ri(MembersInjector<rg> membersInjector) {
        if (f10965a || membersInjector != null) {
            this.f10966b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public rg m13873a() {
        return (rg) MembersInjectors.injectMembers(this.f10966b, new rg());
    }

    public static Factory<rg> m13872a(MembersInjector<rg> membersInjector) {
        return new ri(membersInjector);
    }
}
