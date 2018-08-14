package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class fc implements Factory<fb> {
    static final /* synthetic */ boolean f10099a = (!fc.class.desiredAssertionStatus());
    private final MembersInjector<fb> f10100b;

    public /* synthetic */ Object get() {
        return m13193a();
    }

    public fc(MembersInjector<fb> membersInjector) {
        if (f10099a || membersInjector != null) {
            this.f10100b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public fb m13193a() {
        return (fb) MembersInjectors.injectMembers(this.f10100b, new fb());
    }

    public static Factory<fb> m13192a(MembersInjector<fb> membersInjector) {
        return new fc(membersInjector);
    }
}
