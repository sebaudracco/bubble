package com.vungle.publisher;

import com.vungle.publisher.lf.b;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class lg implements Factory<b> {
    static final /* synthetic */ boolean f10690a = (!lg.class.desiredAssertionStatus());
    private final MembersInjector<b> f10691b;

    public /* synthetic */ Object get() {
        return m13618a();
    }

    public lg(MembersInjector<b> membersInjector) {
        if (f10690a || membersInjector != null) {
            this.f10691b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public b m13618a() {
        return (b) MembersInjectors.injectMembers(this.f10691b, new b());
    }

    public static Factory<b> m13617a(MembersInjector<b> membersInjector) {
        return new lg(membersInjector);
    }
}
