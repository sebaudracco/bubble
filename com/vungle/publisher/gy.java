package com.vungle.publisher;

import com.vungle.publisher.gw.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class gy implements Factory<a> {
    static final /* synthetic */ boolean f10215a = (!gy.class.desiredAssertionStatus());
    private final MembersInjector<a> f10216b;

    public /* synthetic */ Object get() {
        return m13279a();
    }

    public gy(MembersInjector<a> membersInjector) {
        if (f10215a || membersInjector != null) {
            this.f10216b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13279a() {
        return (a) MembersInjectors.injectMembers(this.f10216b, new a());
    }

    public static Factory<a> m13278a(MembersInjector<a> membersInjector) {
        return new gy(membersInjector);
    }
}
