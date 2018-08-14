package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ex implements Factory<ew> {
    static final /* synthetic */ boolean f10089a = (!ex.class.desiredAssertionStatus());
    private final MembersInjector<ew> f10090b;

    public /* synthetic */ Object get() {
        return m13185a();
    }

    public ex(MembersInjector<ew> membersInjector) {
        if (f10089a || membersInjector != null) {
            this.f10090b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ew m13185a() {
        return (ew) MembersInjectors.injectMembers(this.f10090b, new ew());
    }

    public static Factory<ew> m13184a(MembersInjector<ew> membersInjector) {
        return new ex(membersInjector);
    }
}
