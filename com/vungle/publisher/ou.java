package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ou implements Factory<op> {
    static final /* synthetic */ boolean f10881a = (!ou.class.desiredAssertionStatus());
    private final MembersInjector<op> f10882b;

    public /* synthetic */ Object get() {
        return m13801a();
    }

    public ou(MembersInjector<op> membersInjector) {
        if (f10881a || membersInjector != null) {
            this.f10882b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public op m13801a() {
        return (op) MembersInjectors.injectMembers(this.f10882b, new op());
    }

    public static Factory<op> m13800a(MembersInjector<op> membersInjector) {
        return new ou(membersInjector);
    }
}
