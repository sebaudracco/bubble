package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ju implements Factory<jt> {
    static final /* synthetic */ boolean f10588a = (!ju.class.desiredAssertionStatus());
    private final MembersInjector<jt> f10589b;

    public /* synthetic */ Object get() {
        return m13547a();
    }

    public ju(MembersInjector<jt> membersInjector) {
        if (f10588a || membersInjector != null) {
            this.f10589b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public jt m13547a() {
        return (jt) MembersInjectors.injectMembers(this.f10589b, new jt());
    }

    public static Factory<jt> m13546a(MembersInjector<jt> membersInjector) {
        return new ju(membersInjector);
    }
}
