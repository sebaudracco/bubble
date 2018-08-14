package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class gm implements Factory<gl> {
    static final /* synthetic */ boolean f10190a = (!gm.class.desiredAssertionStatus());
    private final MembersInjector<gl> f10191b;

    public /* synthetic */ Object get() {
        return m13261a();
    }

    public gm(MembersInjector<gl> membersInjector) {
        if (f10190a || membersInjector != null) {
            this.f10191b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public gl m13261a() {
        return (gl) MembersInjectors.injectMembers(this.f10191b, new gl());
    }

    public static Factory<gl> m13260a(MembersInjector<gl> membersInjector) {
        return new gm(membersInjector);
    }
}
