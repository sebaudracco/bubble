package com.vungle.publisher.env;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4185p implements Factory<o> {
    static final /* synthetic */ boolean f10038a = (!C4185p.class.desiredAssertionStatus());
    private final MembersInjector<o> f10039b;

    public /* synthetic */ Object get() {
        return m13163a();
    }

    public C4185p(MembersInjector<o> membersInjector) {
        if (f10038a || membersInjector != null) {
            this.f10039b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public o m13163a() {
        return (o) MembersInjectors.injectMembers(this.f10039b, new o());
    }

    public static Factory<o> m13162a(MembersInjector<o> membersInjector) {
        return new C4185p(membersInjector);
    }
}
