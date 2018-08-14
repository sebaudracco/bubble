package com.vungle.publisher;

import com.vungle.publisher.ct.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class cv implements Factory<a> {
    static final /* synthetic */ boolean f9895a = (!cv.class.desiredAssertionStatus());
    private final MembersInjector<a> f9896b;

    public /* synthetic */ Object get() {
        return m12982a();
    }

    public cv(MembersInjector<a> membersInjector) {
        if (f9895a || membersInjector != null) {
            this.f9896b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m12982a() {
        return (a) MembersInjectors.injectMembers(this.f9896b, new a());
    }

    public static Factory<a> m12981a(MembersInjector<a> membersInjector) {
        return new cv(membersInjector);
    }
}
