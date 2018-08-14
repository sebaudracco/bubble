package com.vungle.publisher;

import com.vungle.publisher.ob.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class od implements Factory<a> {
    static final /* synthetic */ boolean f10842a = (!od.class.desiredAssertionStatus());
    private final MembersInjector<a> f10843b;

    public /* synthetic */ Object get() {
        return m13774a();
    }

    public od(MembersInjector<a> membersInjector) {
        if (f10842a || membersInjector != null) {
            this.f10843b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13774a() {
        return (a) MembersInjectors.injectMembers(this.f10843b, new a());
    }

    public static Factory<a> m13773a(MembersInjector<a> membersInjector) {
        return new od(membersInjector);
    }
}
