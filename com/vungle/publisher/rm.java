package com.vungle.publisher;

import com.vungle.publisher.rf.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class rm implements Factory<a> {
    static final /* synthetic */ boolean f10979a = (!rm.class.desiredAssertionStatus());
    private final MembersInjector<a> f10980b;

    public /* synthetic */ Object get() {
        return m13881a();
    }

    public rm(MembersInjector<a> membersInjector) {
        if (f10979a || membersInjector != null) {
            this.f10980b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13881a() {
        return (a) MembersInjectors.injectMembers(this.f10980b, new a());
    }

    public static Factory<a> m13880a(MembersInjector<a> membersInjector) {
        return new rm(membersInjector);
    }
}
