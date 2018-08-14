package com.vungle.publisher;

import com.vungle.publisher.tw.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class tz implements Factory<a> {
    static final /* synthetic */ boolean f11127a = (!tz.class.desiredAssertionStatus());
    private final MembersInjector<a> f11128b;

    public /* synthetic */ Object get() {
        return m13964a();
    }

    public tz(MembersInjector<a> membersInjector) {
        if (f11127a || membersInjector != null) {
            this.f11128b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13964a() {
        return (a) MembersInjectors.injectMembers(this.f11128b, new a());
    }

    public static Factory<a> m13963a(MembersInjector<a> membersInjector) {
        return new tz(membersInjector);
    }
}
