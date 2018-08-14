package com.vungle.publisher;

import com.vungle.publisher.yg.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class yh implements Factory<a> {
    static final /* synthetic */ boolean f11342a = (!yh.class.desiredAssertionStatus());
    private final MembersInjector<a> f11343b;

    public /* synthetic */ Object get() {
        return m14157a();
    }

    public yh(MembersInjector<a> membersInjector) {
        if (f11342a || membersInjector != null) {
            this.f11343b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14157a() {
        return (a) MembersInjectors.injectMembers(this.f11343b, new a());
    }

    public static Factory<a> m14156a(MembersInjector<a> membersInjector) {
        return new yh(membersInjector);
    }
}
