package com.vungle.publisher;

import com.vungle.publisher.hk.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class hm implements Factory<a> {
    static final /* synthetic */ boolean f10275a = (!hm.class.desiredAssertionStatus());
    private final MembersInjector<a> f10276b;

    public /* synthetic */ Object get() {
        return m13306a();
    }

    public hm(MembersInjector<a> membersInjector) {
        if (f10275a || membersInjector != null) {
            this.f10276b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13306a() {
        return (a) MembersInjectors.injectMembers(this.f10276b, new a());
    }

    public static Factory<a> m13305a(MembersInjector<a> membersInjector) {
        return new hm(membersInjector);
    }
}
