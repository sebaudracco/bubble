package com.vungle.publisher;

import com.vungle.publisher.hq.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class iw implements Factory<a> {
    static final /* synthetic */ boolean f10499a = (!iw.class.desiredAssertionStatus());
    private final MembersInjector<a> f10500b;

    public /* synthetic */ Object get() {
        return m13448a();
    }

    public iw(MembersInjector<a> membersInjector) {
        if (f10499a || membersInjector != null) {
            this.f10500b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13448a() {
        return (a) MembersInjectors.injectMembers(this.f10500b, new a());
    }

    public static Factory<a> m13447a(MembersInjector<a> membersInjector) {
        return new iw(membersInjector);
    }
}
