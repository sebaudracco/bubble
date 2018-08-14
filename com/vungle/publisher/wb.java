package com.vungle.publisher;

import com.vungle.publisher.wa.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class wb implements Factory<a> {
    static final /* synthetic */ boolean f11197a = (!wb.class.desiredAssertionStatus());
    private final MembersInjector<a> f11198b;

    public /* synthetic */ Object get() {
        return m14029a();
    }

    public wb(MembersInjector<a> membersInjector) {
        if (f11197a || membersInjector != null) {
            this.f11198b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14029a() {
        return (a) MembersInjectors.injectMembers(this.f11198b, new a());
    }

    public static Factory<a> m14028a(MembersInjector<a> membersInjector) {
        return new wb(membersInjector);
    }
}
