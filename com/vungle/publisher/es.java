package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class es implements Factory<er> {
    static final /* synthetic */ boolean f10077a = (!es.class.desiredAssertionStatus());
    private final MembersInjector<er> f10078b;

    public /* synthetic */ Object get() {
        return m13177a();
    }

    public es(MembersInjector<er> membersInjector) {
        if (f10077a || membersInjector != null) {
            this.f10078b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public er m13177a() {
        return (er) MembersInjectors.injectMembers(this.f10078b, new er());
    }

    public static Factory<er> m13176a(MembersInjector<er> membersInjector) {
        return new es(membersInjector);
    }
}
