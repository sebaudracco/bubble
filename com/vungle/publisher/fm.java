package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class fm implements Factory<fg> {
    static final /* synthetic */ boolean f10120a = (!fm.class.desiredAssertionStatus());
    private final MembersInjector<fg> f10121b;

    public /* synthetic */ Object get() {
        return m13209a();
    }

    public fm(MembersInjector<fg> membersInjector) {
        if (f10120a || membersInjector != null) {
            this.f10121b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public fg m13209a() {
        return (fg) MembersInjectors.injectMembers(this.f10121b, new fg());
    }

    public static Factory<fg> m13208a(MembersInjector<fg> membersInjector) {
        return new fm(membersInjector);
    }
}
