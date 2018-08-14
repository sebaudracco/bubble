package com.vungle.publisher;

import com.vungle.publisher.xb.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class xc implements Factory<a> {
    static final /* synthetic */ boolean f11293a = (!xc.class.desiredAssertionStatus());
    private final MembersInjector<a> f11294b;

    public /* synthetic */ Object get() {
        return m14127a();
    }

    public xc(MembersInjector<a> membersInjector) {
        if (f11293a || membersInjector != null) {
            this.f11294b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14127a() {
        return (a) MembersInjectors.injectMembers(this.f11294b, new a());
    }

    public static Factory<a> m14126a(MembersInjector<a> membersInjector) {
        return new xc(membersInjector);
    }
}
