package com.vungle.publisher;

import com.vungle.publisher.ki.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class kp implements Factory<a> {
    static final /* synthetic */ boolean f10635a = (!kp.class.desiredAssertionStatus());
    private final MembersInjector<a> f10636b;

    public /* synthetic */ Object get() {
        return m13583a();
    }

    public kp(MembersInjector<a> membersInjector) {
        if (f10635a || membersInjector != null) {
            this.f10636b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13583a() {
        return (a) MembersInjectors.injectMembers(this.f10636b, new a());
    }

    public static Factory<a> m13582a(MembersInjector<a> membersInjector) {
        return new kp(membersInjector);
    }
}
