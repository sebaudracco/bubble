package com.vungle.publisher;

import com.vungle.publisher.gv.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class hc implements Factory<a> {
    static final /* synthetic */ boolean f10244a = (!hc.class.desiredAssertionStatus());
    private final MembersInjector<a> f10245b;

    public /* synthetic */ Object get() {
        return m13289a();
    }

    public hc(MembersInjector<a> membersInjector) {
        if (f10244a || membersInjector != null) {
            this.f10245b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13289a() {
        return (a) MembersInjectors.injectMembers(this.f10245b, new a());
    }

    public static Factory<a> m13288a(MembersInjector<a> membersInjector) {
        return new hc(membersInjector);
    }
}
