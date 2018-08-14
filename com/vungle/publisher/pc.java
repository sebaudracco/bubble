package com.vungle.publisher;

import com.vungle.publisher.oy.a.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class pc implements Factory<a> {
    static final /* synthetic */ boolean f10913a = (!pc.class.desiredAssertionStatus());
    private final MembersInjector<a> f10914b;

    public /* synthetic */ Object get() {
        return m13815a();
    }

    public pc(MembersInjector<a> membersInjector) {
        if (f10913a || membersInjector != null) {
            this.f10914b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13815a() {
        return (a) MembersInjectors.injectMembers(this.f10914b, new a());
    }

    public static Factory<a> m13814a(MembersInjector<a> membersInjector) {
        return new pc(membersInjector);
    }
}
