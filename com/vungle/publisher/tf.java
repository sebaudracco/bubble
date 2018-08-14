package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class tf implements Factory<te> {
    static final /* synthetic */ boolean f11083a = (!tf.class.desiredAssertionStatus());
    private final MembersInjector<te> f11084b;

    public /* synthetic */ Object get() {
        return m13929a();
    }

    public tf(MembersInjector<te> membersInjector) {
        if (f11083a || membersInjector != null) {
            this.f11084b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public te m13929a() {
        return (te) MembersInjectors.injectMembers(this.f11084b, new te());
    }

    public static Factory<te> m13928a(MembersInjector<te> membersInjector) {
        return new tf(membersInjector);
    }
}
