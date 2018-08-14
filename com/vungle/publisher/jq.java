package com.vungle.publisher;

import com.vungle.publisher.jo.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class jq implements Factory<a> {
    static final /* synthetic */ boolean f10575a = (!jq.class.desiredAssertionStatus());
    private final MembersInjector<a> f10576b;

    public /* synthetic */ Object get() {
        return m13541a();
    }

    public jq(MembersInjector<a> membersInjector) {
        if (f10575a || membersInjector != null) {
            this.f10576b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13541a() {
        return (a) MembersInjectors.injectMembers(this.f10576b, new a());
    }

    public static Factory<a> m13540a(MembersInjector<a> membersInjector) {
        return new jq(membersInjector);
    }
}
