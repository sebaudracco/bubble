package com.vungle.publisher;

import com.vungle.publisher.fb.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class fd implements Factory<a> {
    static final /* synthetic */ boolean f10101a = (!fd.class.desiredAssertionStatus());
    private final MembersInjector<a> f10102b;

    public /* synthetic */ Object get() {
        return m13195a();
    }

    public fd(MembersInjector<a> membersInjector) {
        if (f10101a || membersInjector != null) {
            this.f10102b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13195a() {
        return (a) MembersInjectors.injectMembers(this.f10102b, new a());
    }

    public static Factory<a> m13194a(MembersInjector<a> membersInjector) {
        return new fd(membersInjector);
    }
}
