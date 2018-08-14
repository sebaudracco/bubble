package com.vungle.publisher;

import com.vungle.publisher.er.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class et implements Factory<a> {
    static final /* synthetic */ boolean f10079a = (!et.class.desiredAssertionStatus());
    private final MembersInjector<a> f10080b;

    public /* synthetic */ Object get() {
        return m13179a();
    }

    public et(MembersInjector<a> membersInjector) {
        if (f10079a || membersInjector != null) {
            this.f10080b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13179a() {
        return (a) MembersInjectors.injectMembers(this.f10080b, new a());
    }

    public static Factory<a> m13178a(MembersInjector<a> membersInjector) {
        return new et(membersInjector);
    }
}
