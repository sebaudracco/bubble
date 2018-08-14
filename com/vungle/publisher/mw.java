package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class mw implements Factory<mv> {
    static final /* synthetic */ boolean f10766a = (!mw.class.desiredAssertionStatus());
    private final MembersInjector<mv> f10767b;

    public /* synthetic */ Object get() {
        return m13715a();
    }

    public mw(MembersInjector<mv> membersInjector) {
        if (f10766a || membersInjector != null) {
            this.f10767b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public mv m13715a() {
        return (mv) MembersInjectors.injectMembers(this.f10767b, new mv());
    }

    public static Factory<mv> m13714a(MembersInjector<mv> membersInjector) {
        return new mw(membersInjector);
    }
}
