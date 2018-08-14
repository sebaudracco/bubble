package com.vungle.publisher;

import com.vungle.publisher.env.C4181i;
import com.vungle.publisher.env.o;
import com.vungle.publisher.env.r;
import com.vungle.publisher.log.g;
import com.vungle.publisher.py.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class VunglePubBase_MembersInjector implements MembersInjector<VunglePubBase> {
    static final /* synthetic */ boolean f9753a = (!VunglePubBase_MembersInjector.class.desiredAssertionStatus());
    private final Provider<c> f9754b;
    private final Provider<InitializationEventListener> f9755c;
    private final Provider<a> f9756d;
    private final Provider<qo> f9757e;
    private final Provider<ci> f9758f;
    private final Provider<C4181i> f9759g;
    private final Provider<qg> f9760h;
    private final Provider<AdConfig> f9761i;
    private final Provider<u> f9762j;
    private final Provider<o> f9763k;
    private final Provider<r> f9764l;
    private final Provider<mj.a> f9765m;
    private final Provider<g> f9766n;

    public VunglePubBase_MembersInjector(Provider<c> adManagerProvider, Provider<InitializationEventListener> initializationEventListenerProvider, Provider<a> clientInitListenerAdapterFactoryProvider, Provider<qo> cacheManagerProvider, Provider<ci> databaseHelperProvider, Provider<C4181i> deviceProvider, Provider<qg> eventBusProvider, Provider<AdConfig> globalAdConfigProvider, Provider<u> safeBundleAdConfigFactoryProvider, Provider<o> sdkConfigProvider, Provider<r> sdkStateProvider, Provider<mj.a> dummyWebViewFactoryProvider, Provider<g> loggerProvider) {
        if (f9753a || adManagerProvider != null) {
            this.f9754b = adManagerProvider;
            if (f9753a || initializationEventListenerProvider != null) {
                this.f9755c = initializationEventListenerProvider;
                if (f9753a || clientInitListenerAdapterFactoryProvider != null) {
                    this.f9756d = clientInitListenerAdapterFactoryProvider;
                    if (f9753a || cacheManagerProvider != null) {
                        this.f9757e = cacheManagerProvider;
                        if (f9753a || databaseHelperProvider != null) {
                            this.f9758f = databaseHelperProvider;
                            if (f9753a || deviceProvider != null) {
                                this.f9759g = deviceProvider;
                                if (f9753a || eventBusProvider != null) {
                                    this.f9760h = eventBusProvider;
                                    if (f9753a || globalAdConfigProvider != null) {
                                        this.f9761i = globalAdConfigProvider;
                                        if (f9753a || safeBundleAdConfigFactoryProvider != null) {
                                            this.f9762j = safeBundleAdConfigFactoryProvider;
                                            if (f9753a || sdkConfigProvider != null) {
                                                this.f9763k = sdkConfigProvider;
                                                if (f9753a || sdkStateProvider != null) {
                                                    this.f9764l = sdkStateProvider;
                                                    if (f9753a || dummyWebViewFactoryProvider != null) {
                                                        this.f9765m = dummyWebViewFactoryProvider;
                                                        if (f9753a || loggerProvider != null) {
                                                            this.f9766n = loggerProvider;
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

    public static MembersInjector<VunglePubBase> create(Provider<c> adManagerProvider, Provider<InitializationEventListener> initializationEventListenerProvider, Provider<a> clientInitListenerAdapterFactoryProvider, Provider<qo> cacheManagerProvider, Provider<ci> databaseHelperProvider, Provider<C4181i> deviceProvider, Provider<qg> eventBusProvider, Provider<AdConfig> globalAdConfigProvider, Provider<u> safeBundleAdConfigFactoryProvider, Provider<o> sdkConfigProvider, Provider<r> sdkStateProvider, Provider<mj.a> dummyWebViewFactoryProvider, Provider<g> loggerProvider) {
        return new VunglePubBase_MembersInjector(adManagerProvider, initializationEventListenerProvider, clientInitListenerAdapterFactoryProvider, cacheManagerProvider, databaseHelperProvider, deviceProvider, eventBusProvider, globalAdConfigProvider, safeBundleAdConfigFactoryProvider, sdkConfigProvider, sdkStateProvider, dummyWebViewFactoryProvider, loggerProvider);
    }

    public void injectMembers(VunglePubBase instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.a = (c) this.f9754b.get();
        instance.b = (InitializationEventListener) this.f9755c.get();
        instance.c = (a) this.f9756d.get();
        instance.d = (qo) this.f9757e.get();
        instance.e = (ci) this.f9758f.get();
        instance.f = (C4181i) this.f9759g.get();
        instance.g = (qg) this.f9760h.get();
        instance.h = (AdConfig) this.f9761i.get();
        instance.i = (u) this.f9762j.get();
        instance.j = (o) this.f9763k.get();
        instance.k = (r) this.f9764l.get();
        instance.l = (mj.a) this.f9765m.get();
        instance.m = (g) this.f9766n.get();
    }

    public static void injectAdManager(VunglePubBase instance, Provider<c> adManagerProvider) {
        instance.a = (c) adManagerProvider.get();
    }

    public static void injectInitializationEventListener(VunglePubBase instance, Provider<InitializationEventListener> initializationEventListenerProvider) {
        instance.b = (InitializationEventListener) initializationEventListenerProvider.get();
    }

    public static void injectClientInitListenerAdapterFactory(VunglePubBase instance, Provider<a> clientInitListenerAdapterFactoryProvider) {
        instance.c = (a) clientInitListenerAdapterFactoryProvider.get();
    }

    public static void injectCacheManager(VunglePubBase instance, Provider<qo> cacheManagerProvider) {
        instance.d = (qo) cacheManagerProvider.get();
    }

    public static void injectDatabaseHelper(VunglePubBase instance, Provider<ci> databaseHelperProvider) {
        instance.e = (ci) databaseHelperProvider.get();
    }

    public static void injectDevice(VunglePubBase instance, Provider<C4181i> deviceProvider) {
        instance.f = (C4181i) deviceProvider.get();
    }

    public static void injectEventBus(VunglePubBase instance, Provider<qg> eventBusProvider) {
        instance.g = (qg) eventBusProvider.get();
    }

    public static void injectGlobalAdConfig(VunglePubBase instance, Provider<AdConfig> globalAdConfigProvider) {
        instance.h = (AdConfig) globalAdConfigProvider.get();
    }

    public static void injectSafeBundleAdConfigFactory(VunglePubBase instance, Provider<u> safeBundleAdConfigFactoryProvider) {
        instance.i = (u) safeBundleAdConfigFactoryProvider.get();
    }

    public static void injectSdkConfig(VunglePubBase instance, Provider<o> sdkConfigProvider) {
        instance.j = (o) sdkConfigProvider.get();
    }

    public static void injectSdkState(VunglePubBase instance, Provider<r> sdkStateProvider) {
        instance.k = (r) sdkStateProvider.get();
    }

    public static void injectDummyWebViewFactory(VunglePubBase instance, Provider<mj.a> dummyWebViewFactoryProvider) {
        instance.l = (mj.a) dummyWebViewFactoryProvider.get();
    }

    public static void injectLogger(VunglePubBase instance, Provider<g> loggerProvider) {
        instance.m = (g) loggerProvider.get();
    }
}
