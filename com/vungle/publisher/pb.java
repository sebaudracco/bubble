package com.vungle.publisher;

import com.vungle.publisher.oy.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class pb implements Factory<a> {
    static final /* synthetic */ boolean f10911a = (!pb.class.desiredAssertionStatus());
    private final MembersInjector<a> f10912b;

    public /* synthetic */ Object get() {
        return m13813a();
    }

    public pb(MembersInjector<a> membersInjector) {
        if (f10911a || membersInjector != null) {
            this.f10912b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13813a() {
        return (a) MembersInjectors.injectMembers(this.f10912b, new a());
    }

    public static Factory<a> m13812a(MembersInjector<a> membersInjector) {
        return new pb(membersInjector);
    }
}
