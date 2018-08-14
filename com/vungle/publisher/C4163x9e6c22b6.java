package com.vungle.publisher;

import com.vungle.publisher.InitializationEventListener.a;
import com.vungle.publisher.env.r;
import com.vungle.publisher.log.g;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4163x9e6c22b6 implements MembersInjector<a> {
    static final /* synthetic */ boolean f9728a = (!C4163x9e6c22b6.class.desiredAssertionStatus());
    private final Provider<qg> f9729b;
    private final Provider<c> f9730c;
    private final Provider<yk> f9731d;
    private final Provider<r> f9732e;
    private final Provider<g> f9733f;
    private final Provider<vc> f9734g;
    private final Provider<bw> f9735h;

    public C4163x9e6c22b6(Provider<qg> eventBusProvider, Provider<c> adManagerProvider, Provider<yk> reportManagerProvider, Provider<r> sdkStateProvider, Provider<g> loggingManagerProvider, Provider<vc> protocolHttpGatewayProvider, Provider<bw> executorProvider) {
        if (f9728a || eventBusProvider != null) {
            this.f9729b = eventBusProvider;
            if (f9728a || adManagerProvider != null) {
                this.f9730c = adManagerProvider;
                if (f9728a || reportManagerProvider != null) {
                    this.f9731d = reportManagerProvider;
                    if (f9728a || sdkStateProvider != null) {
                        this.f9732e = sdkStateProvider;
                        if (f9728a || loggingManagerProvider != null) {
                            this.f9733f = loggingManagerProvider;
                            if (f9728a || protocolHttpGatewayProvider != null) {
                                this.f9734g = protocolHttpGatewayProvider;
                                if (f9728a || executorProvider != null) {
                                    this.f9735h = executorProvider;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> create(Provider<qg> eventBusProvider, Provider<c> adManagerProvider, Provider<yk> reportManagerProvider, Provider<r> sdkStateProvider, Provider<g> loggingManagerProvider, Provider<vc> protocolHttpGatewayProvider, Provider<bw> executorProvider) {
        return new C4163x9e6c22b6(eventBusProvider, adManagerProvider, reportManagerProvider, sdkStateProvider, loggingManagerProvider, protocolHttpGatewayProvider, executorProvider);
    }

    public void injectMembers(a instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.eventBus = (qg) this.f9729b.get();
        instance.a = (c) this.f9730c.get();
        instance.b = (yk) this.f9731d.get();
        instance.c = (r) this.f9732e.get();
        instance.d = (g) this.f9733f.get();
        instance.e = (vc) this.f9734g.get();
        instance.f = (bw) this.f9735h.get();
    }

    public static void injectAdManager(a instance, Provider<c> adManagerProvider) {
        instance.a = (c) adManagerProvider.get();
    }

    public static void injectReportManager(a instance, Provider<yk> reportManagerProvider) {
        instance.b = (yk) reportManagerProvider.get();
    }

    public static void injectSdkState(a instance, Provider<r> sdkStateProvider) {
        instance.c = (r) sdkStateProvider.get();
    }

    public static void injectLoggingManager(a instance, Provider<g> loggingManagerProvider) {
        instance.d = (g) loggingManagerProvider.get();
    }

    public static void injectProtocolHttpGateway(a instance, Provider<vc> protocolHttpGatewayProvider) {
        instance.e = (vc) protocolHttpGatewayProvider.get();
    }

    public static void injectExecutor(a instance, Provider<bw> executorProvider) {
        instance.f = (bw) executorProvider.get();
    }
}
