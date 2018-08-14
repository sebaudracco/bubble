package com.vungle.publisher;

import com.vungle.publisher.li.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class lk implements Factory<a> {
    static final /* synthetic */ boolean f10697a = (!lk.class.desiredAssertionStatus());
    private final MembersInjector<a> f10698b;

    public /* synthetic */ Object get() {
        return m13624a();
    }

    public lk(MembersInjector<a> membersInjector) {
        if (f10697a || membersInjector != null) {
            this.f10698b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13624a() {
        return (a) MembersInjectors.injectMembers(this.f10698b, new a());
    }

    public static Factory<a> m13623a(MembersInjector<a> membersInjector) {
        return new lk(membersInjector);
    }
}
