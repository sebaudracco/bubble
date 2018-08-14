package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class in implements Factory<im> {
    static final /* synthetic */ boolean f10360a = (!in.class.desiredAssertionStatus());
    private final MembersInjector<im> f10361b;

    public /* synthetic */ Object get() {
        return m13365a();
    }

    public in(MembersInjector<im> membersInjector) {
        if (f10360a || membersInjector != null) {
            this.f10361b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public im m13365a() {
        return (im) MembersInjectors.injectMembers(this.f10361b, new im());
    }

    public static Factory<im> m13364a(MembersInjector<im> membersInjector) {
        return new in(membersInjector);
    }
}
