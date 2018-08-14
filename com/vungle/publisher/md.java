package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class md implements Factory<mc> {
    static final /* synthetic */ boolean f10743a = (!md.class.desiredAssertionStatus());
    private final MembersInjector<mc> f10744b;

    public /* synthetic */ Object get() {
        return m13677a();
    }

    public md(MembersInjector<mc> membersInjector) {
        if (f10743a || membersInjector != null) {
            this.f10744b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public mc m13677a() {
        return (mc) MembersInjectors.injectMembers(this.f10744b, new mc());
    }

    public static Factory<mc> m13676a(MembersInjector<mc> membersInjector) {
        return new md(membersInjector);
    }
}
