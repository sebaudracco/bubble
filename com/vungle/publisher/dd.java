package com.vungle.publisher;

import com.vungle.publisher.cn.b;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class dd implements Factory<b> {
    static final /* synthetic */ boolean f9929a = (!dd.class.desiredAssertionStatus());
    private final MembersInjector<b> f9930b;

    public /* synthetic */ Object get() {
        return m13041a();
    }

    public dd(MembersInjector<b> membersInjector) {
        if (f9929a || membersInjector != null) {
            this.f9930b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public b m13041a() {
        return (b) MembersInjectors.injectMembers(this.f9930b, new b());
    }

    public static Factory<b> m13040a(MembersInjector<b> membersInjector) {
        return new dd(membersInjector);
    }
}
