package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class tb implements Factory<ta> {
    static final /* synthetic */ boolean f11074a = (!tb.class.desiredAssertionStatus());
    private final MembersInjector<ta> f11075b;

    public /* synthetic */ Object get() {
        return m13923a();
    }

    public tb(MembersInjector<ta> membersInjector) {
        if (f11074a || membersInjector != null) {
            this.f11075b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ta m13923a() {
        return (ta) MembersInjectors.injectMembers(this.f11075b, new ta());
    }

    public static Factory<ta> m13922a(MembersInjector<ta> membersInjector) {
        return new tb(membersInjector);
    }
}
