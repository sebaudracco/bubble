package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class fv implements Factory<el> {
    static final /* synthetic */ boolean f10146a = (!fv.class.desiredAssertionStatus());
    private final MembersInjector<el> f10147b;

    public /* synthetic */ Object get() {
        return m13225a();
    }

    public fv(MembersInjector<el> membersInjector) {
        if (f10146a || membersInjector != null) {
            this.f10147b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public el m13225a() {
        return (el) MembersInjectors.injectMembers(this.f10147b, new el());
    }

    public static Factory<el> m13224a(MembersInjector<el> membersInjector) {
        return new fv(membersInjector);
    }
}
