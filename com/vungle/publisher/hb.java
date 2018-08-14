package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class hb implements Factory<gv> {
    static final /* synthetic */ boolean f10242a = (!hb.class.desiredAssertionStatus());
    private final MembersInjector<gv> f10243b;

    public /* synthetic */ Object get() {
        return m13287a();
    }

    public hb(MembersInjector<gv> membersInjector) {
        if (f10242a || membersInjector != null) {
            this.f10243b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public gv m13287a() {
        return (gv) MembersInjectors.injectMembers(this.f10243b, new gv());
    }

    public static Factory<gv> m13286a(MembersInjector<gv> membersInjector) {
        return new hb(membersInjector);
    }
}
