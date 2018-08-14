package com.vungle.publisher;

import com.vungle.publisher.InitializationEventListener.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4162xbf8c1153 implements Factory<a> {
    static final /* synthetic */ boolean f9726a = (!C4162xbf8c1153.class.desiredAssertionStatus());
    private final MembersInjector<a> f9727b;

    public C4162xbf8c1153(MembersInjector<a> initialConfigUpdatedEventListenerMembersInjector) {
        if (f9726a || initialConfigUpdatedEventListenerMembersInjector != null) {
            this.f9727b = initialConfigUpdatedEventListenerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a get() {
        return (a) MembersInjectors.injectMembers(this.f9727b, new a());
    }

    public static Factory<a> create(MembersInjector<a> initialConfigUpdatedEventListenerMembersInjector) {
        return new C4162xbf8c1153(initialConfigUpdatedEventListenerMembersInjector);
    }
}
