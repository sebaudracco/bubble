package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class tl implements Factory<tj> {
    static final /* synthetic */ boolean f11094a = (!tl.class.desiredAssertionStatus());
    private final MembersInjector<tj> f11095b;

    public /* synthetic */ Object get() {
        return m13946a();
    }

    public tl(MembersInjector<tj> membersInjector) {
        if (f11094a || membersInjector != null) {
            this.f11095b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public tj m13946a() {
        return (tj) MembersInjectors.injectMembers(this.f11095b, new tj());
    }

    public static Factory<tj> m13945a(MembersInjector<tj> membersInjector) {
        return new tl(membersInjector);
    }
}
