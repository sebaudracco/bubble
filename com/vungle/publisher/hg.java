package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class hg implements Factory<gk> {
    static final /* synthetic */ boolean f10258a = (!hg.class.desiredAssertionStatus());
    private final MembersInjector<gk> f10259b;

    public /* synthetic */ Object get() {
        return m13296a();
    }

    public hg(MembersInjector<gk> membersInjector) {
        if (f10258a || membersInjector != null) {
            this.f10259b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public gk m13296a() {
        return (gk) MembersInjectors.injectMembers(this.f10259b, new gk());
    }

    public static Factory<gk> m13295a(MembersInjector<gk> membersInjector) {
        return new hg(membersInjector);
    }
}
