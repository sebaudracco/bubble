package com.vungle.publisher;

import com.vungle.publisher.nk.a.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class np implements Factory<a> {
    static final /* synthetic */ boolean f10815a = (!np.class.desiredAssertionStatus());
    private final MembersInjector<a> f10816b;

    public /* synthetic */ Object get() {
        return m13754a();
    }

    public np(MembersInjector<a> membersInjector) {
        if (f10815a || membersInjector != null) {
            this.f10816b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13754a() {
        return (a) MembersInjectors.injectMembers(this.f10816b, new a());
    }

    public static Factory<a> m13753a(MembersInjector<a> membersInjector) {
        return new np(membersInjector);
    }
}
