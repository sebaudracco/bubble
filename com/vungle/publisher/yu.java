package com.vungle.publisher;

import com.vungle.publisher.ys.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class yu implements Factory<a> {
    static final /* synthetic */ boolean f11373a = (!yu.class.desiredAssertionStatus());
    private final MembersInjector<a> f11374b;

    public /* synthetic */ Object get() {
        return m14175a();
    }

    public yu(MembersInjector<a> membersInjector) {
        if (f11373a || membersInjector != null) {
            this.f11374b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14175a() {
        return (a) MembersInjectors.injectMembers(this.f11374b, new a());
    }

    public static Factory<a> m14174a(MembersInjector<a> membersInjector) {
        return new yu(membersInjector);
    }
}
