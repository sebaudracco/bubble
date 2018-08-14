package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class lq implements Factory<lp> {
    static final /* synthetic */ boolean f10723a = (!lq.class.desiredAssertionStatus());
    private final MembersInjector<lp> f10724b;

    public /* synthetic */ Object get() {
        return m13663a();
    }

    public lq(MembersInjector<lp> membersInjector) {
        if (f10723a || membersInjector != null) {
            this.f10724b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public lp m13663a() {
        return (lp) MembersInjectors.injectMembers(this.f10724b, new lp());
    }

    public static Factory<lp> m13662a(MembersInjector<lp> membersInjector) {
        return new lq(membersInjector);
    }
}
