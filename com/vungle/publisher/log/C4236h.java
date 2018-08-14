package com.vungle.publisher.log;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4236h implements Factory<g> {
    static final /* synthetic */ boolean f10714a = (!C4236h.class.desiredAssertionStatus());
    private final MembersInjector<g> f10715b;

    public /* synthetic */ Object get() {
        return m13659a();
    }

    public C4236h(MembersInjector<g> membersInjector) {
        if (f10714a || membersInjector != null) {
            this.f10715b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public g m13659a() {
        return (g) MembersInjectors.injectMembers(this.f10715b, new g());
    }

    public static Factory<g> m13658a(MembersInjector<g> membersInjector) {
        return new C4236h(membersInjector);
    }
}
