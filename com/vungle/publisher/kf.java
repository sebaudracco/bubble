package com.vungle.publisher;

import com.vungle.publisher.kd.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class kf implements Factory<a> {
    static final /* synthetic */ boolean f10614a = (!kf.class.desiredAssertionStatus());
    private final MembersInjector<a> f10615b;

    public /* synthetic */ Object get() {
        return m13567a();
    }

    public kf(MembersInjector<a> membersInjector) {
        if (f10614a || membersInjector != null) {
            this.f10615b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13567a() {
        return (a) MembersInjectors.injectMembers(this.f10615b, new a());
    }

    public static Factory<a> m13566a(MembersInjector<a> membersInjector) {
        return new kf(membersInjector);
    }
}
