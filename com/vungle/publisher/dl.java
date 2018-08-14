package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class dl implements Factory<dk> {
    static final /* synthetic */ boolean f9946a = (!dl.class.desiredAssertionStatus());
    private final MembersInjector<dk> f9947b;

    public /* synthetic */ Object get() {
        return m13053a();
    }

    public dl(MembersInjector<dk> membersInjector) {
        if (f9946a || membersInjector != null) {
            this.f9947b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public dk m13053a() {
        return (dk) MembersInjectors.injectMembers(this.f9947b, new dk());
    }

    public static Factory<dk> m13052a(MembersInjector<dk> membersInjector) {
        return new dl(membersInjector);
    }
}
