package com.vungle.publisher;

import com.vungle.publisher.ew.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ey implements Factory<a> {
    static final /* synthetic */ boolean f10091a = (!ey.class.desiredAssertionStatus());
    private final MembersInjector<a> f10092b;

    public /* synthetic */ Object get() {
        return m13187a();
    }

    public ey(MembersInjector<a> membersInjector) {
        if (f10091a || membersInjector != null) {
            this.f10092b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13187a() {
        return (a) MembersInjectors.injectMembers(this.f10092b, new a());
    }

    public static Factory<a> m13186a(MembersInjector<a> membersInjector) {
        return new ey(membersInjector);
    }
}
