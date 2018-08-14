package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class oc implements Factory<ob> {
    static final /* synthetic */ boolean f10840a = (!oc.class.desiredAssertionStatus());
    private final MembersInjector<ob> f10841b;

    public /* synthetic */ Object get() {
        return m13772a();
    }

    public oc(MembersInjector<ob> membersInjector) {
        if (f10840a || membersInjector != null) {
            this.f10841b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ob m13772a() {
        return (ob) MembersInjectors.injectMembers(this.f10841b, new ob());
    }

    public static Factory<ob> m13771a(MembersInjector<ob> membersInjector) {
        return new oc(membersInjector);
    }
}
