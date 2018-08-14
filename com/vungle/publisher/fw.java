package com.vungle.publisher;

import com.vungle.publisher.el.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class fw implements Factory<a> {
    static final /* synthetic */ boolean f10148a = (!fw.class.desiredAssertionStatus());
    private final MembersInjector<a> f10149b;

    public /* synthetic */ Object get() {
        return m13227a();
    }

    public fw(MembersInjector<a> membersInjector) {
        if (f10148a || membersInjector != null) {
            this.f10149b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13227a() {
        return (a) MembersInjectors.injectMembers(this.f10149b, new a());
    }

    public static Factory<a> m13226a(MembersInjector<a> membersInjector) {
        return new fw(membersInjector);
    }
}
