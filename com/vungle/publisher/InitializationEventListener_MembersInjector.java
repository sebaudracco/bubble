package com.vungle.publisher;

import com.vungle.publisher.InitializationEventListener.a;
import com.vungle.publisher.env.r;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class InitializationEventListener_MembersInjector implements MembersInjector<InitializationEventListener> {
    static final /* synthetic */ boolean f9736a = (!InitializationEventListener_MembersInjector.class.desiredAssertionStatus());
    private final Provider<qg> f9737b;
    private final Provider<bw> f9738c;
    private final Provider<vc> f9739d;
    private final Provider<a> f9740e;
    private final Provider<r> f9741f;

    public InitializationEventListener_MembersInjector(Provider<qg> eventBusProvider, Provider<bw> executorProvider, Provider<vc> protocolHttpGatewayProvider, Provider<a> initialConfigUpdatedEventListenerProvider, Provider<r> sdkStateProvider) {
        if (f9736a || eventBusProvider != null) {
            this.f9737b = eventBusProvider;
            if (f9736a || executorProvider != null) {
                this.f9738c = executorProvider;
                if (f9736a || protocolHttpGatewayProvider != null) {
                    this.f9739d = protocolHttpGatewayProvider;
                    if (f9736a || initialConfigUpdatedEventListenerProvider != null) {
                        this.f9740e = initialConfigUpdatedEventListenerProvider;
                        if (f9736a || sdkStateProvider != null) {
                            this.f9741f = sdkStateProvider;
                            return;
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<InitializationEventListener> create(Provider<qg> eventBusProvider, Provider<bw> executorProvider, Provider<vc> protocolHttpGatewayProvider, Provider<a> initialConfigUpdatedEventListenerProvider, Provider<r> sdkStateProvider) {
        return new InitializationEventListener_MembersInjector(eventBusProvider, executorProvider, protocolHttpGatewayProvider, initialConfigUpdatedEventListenerProvider, sdkStateProvider);
    }

    public void injectMembers(InitializationEventListener instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.eventBus = (qg) this.f9737b.get();
        instance.a = (bw) this.f9738c.get();
        instance.b = (vc) this.f9739d.get();
        instance.c = (a) this.f9740e.get();
        instance.d = (r) this.f9741f.get();
    }

    public static void injectExecutor(InitializationEventListener instance, Provider<bw> executorProvider) {
        instance.a = (bw) executorProvider.get();
    }

    public static void injectProtocolHttpGateway(InitializationEventListener instance, Provider<vc> protocolHttpGatewayProvider) {
        instance.b = (vc) protocolHttpGatewayProvider.get();
    }

    public static void injectInitialConfigUpdatedEventListener(InitializationEventListener instance, Provider<a> initialConfigUpdatedEventListenerProvider) {
        instance.c = (a) initialConfigUpdatedEventListenerProvider.get();
    }

    public static void injectSdkState(InitializationEventListener instance, Provider<r> sdkStateProvider) {
        instance.d = (r) sdkStateProvider.get();
    }
}
