package com.vungle.publisher;

import com.vungle.publisher.cz.b;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class db implements Factory<b> {
    static final /* synthetic */ boolean f9921a = (!db.class.desiredAssertionStatus());
    private final MembersInjector<b> f9922b;

    public /* synthetic */ Object get() {
        return m13037a();
    }

    public db(MembersInjector<b> membersInjector) {
        if (f9921a || membersInjector != null) {
            this.f9922b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public b m13037a() {
        return (b) MembersInjectors.injectMembers(this.f9922b, new b());
    }

    public static Factory<b> m13036a(MembersInjector<b> membersInjector) {
        return new db(membersInjector);
    }
}
