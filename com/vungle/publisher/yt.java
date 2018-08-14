package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class yt implements Factory<ys> {
    static final /* synthetic */ boolean f11371a = (!yt.class.desiredAssertionStatus());
    private final MembersInjector<ys> f11372b;

    public /* synthetic */ Object get() {
        return m14173a();
    }

    public yt(MembersInjector<ys> membersInjector) {
        if (f11371a || membersInjector != null) {
            this.f11372b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ys m14173a() {
        return (ys) MembersInjectors.injectMembers(this.f11372b, new ys());
    }

    public static Factory<ys> m14172a(MembersInjector<ys> membersInjector) {
        return new yt(membersInjector);
    }
}
