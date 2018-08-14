package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class lj implements Factory<li> {
    static final /* synthetic */ boolean f10695a = (!lj.class.desiredAssertionStatus());
    private final MembersInjector<li> f10696b;

    public /* synthetic */ Object get() {
        return m13622a();
    }

    public lj(MembersInjector<li> membersInjector) {
        if (f10695a || membersInjector != null) {
            this.f10696b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public li m13622a() {
        return (li) MembersInjectors.injectMembers(this.f10696b, new li());
    }

    public static Factory<li> m13621a(MembersInjector<li> membersInjector) {
        return new lj(membersInjector);
    }
}
