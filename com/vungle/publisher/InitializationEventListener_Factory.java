package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class InitializationEventListener_Factory implements Factory<InitializationEventListener> {
    static final /* synthetic */ boolean f9724a = (!InitializationEventListener_Factory.class.desiredAssertionStatus());
    private final MembersInjector<InitializationEventListener> f9725b;

    public InitializationEventListener_Factory(MembersInjector<InitializationEventListener> initializationEventListenerMembersInjector) {
        if (f9724a || initializationEventListenerMembersInjector != null) {
            this.f9725b = initializationEventListenerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public InitializationEventListener get() {
        return (InitializationEventListener) MembersInjectors.injectMembers(this.f9725b, new InitializationEventListener());
    }

    public static Factory<InitializationEventListener> create(MembersInjector<InitializationEventListener> initializationEventListenerMembersInjector) {
        return new InitializationEventListener_Factory(initializationEventListenerMembersInjector);
    }
}
