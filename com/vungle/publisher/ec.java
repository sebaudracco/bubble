package com.vungle.publisher;

import com.vungle.publisher.eb.b;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ec implements Factory<b> {
    static final /* synthetic */ boolean f9977a = (!ec.class.desiredAssertionStatus());
    private final MembersInjector<b> f9978b;

    public /* synthetic */ Object get() {
        return m13093a();
    }

    public ec(MembersInjector<b> membersInjector) {
        if (f9977a || membersInjector != null) {
            this.f9978b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public b m13093a() {
        return (b) MembersInjectors.injectMembers(this.f9978b, new b());
    }

    public static Factory<b> m13092a(MembersInjector<b> membersInjector) {
        return new ec(membersInjector);
    }
}
