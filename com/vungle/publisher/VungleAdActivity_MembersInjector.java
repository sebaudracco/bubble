package com.vungle.publisher;

import com.vungle.publisher.cn.b;
import com.vungle.publisher.env.r;
import com.vungle.publisher.log.g;
import com.vungle.publisher.mg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class VungleAdActivity_MembersInjector implements MembersInjector<VungleAdActivity> {
    static final /* synthetic */ boolean f9743a = (!VungleAdActivity_MembersInjector.class.desiredAssertionStatus());
    private final Provider<qg> f9744b;
    private final Provider<bz> f9745c;
    private final Provider<r> f9746d;
    private final Provider<b> f9747e;
    private final Provider<lm> f9748f;
    private final Provider<a> f9749g;
    private final Provider<u> f9750h;
    private final Provider<g> f9751i;

    public VungleAdActivity_MembersInjector(Provider<qg> eventBusProvider, Provider<bz> uiExecutorProvider, Provider<r> sdkStateProvider, Provider<b> adMediatorProvider, Provider<lm> audioHelperProvider, Provider<a> adPresenterMediatorProvider, Provider<u> adConfigFactoryProvider, Provider<g> loggerProvider) {
        if (f9743a || eventBusProvider != null) {
            this.f9744b = eventBusProvider;
            if (f9743a || uiExecutorProvider != null) {
                this.f9745c = uiExecutorProvider;
                if (f9743a || sdkStateProvider != null) {
                    this.f9746d = sdkStateProvider;
                    if (f9743a || adMediatorProvider != null) {
                        this.f9747e = adMediatorProvider;
                        if (f9743a || audioHelperProvider != null) {
                            this.f9748f = audioHelperProvider;
                            if (f9743a || adPresenterMediatorProvider != null) {
                                this.f9749g = adPresenterMediatorProvider;
                                if (f9743a || adConfigFactoryProvider != null) {
                                    this.f9750h = adConfigFactoryProvider;
                                    if (f9743a || loggerProvider != null) {
                                        this.f9751i = loggerProvider;
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

    public static MembersInjector<VungleAdActivity> create(Provider<qg> eventBusProvider, Provider<bz> uiExecutorProvider, Provider<r> sdkStateProvider, Provider<b> adMediatorProvider, Provider<lm> audioHelperProvider, Provider<a> adPresenterMediatorProvider, Provider<u> adConfigFactoryProvider, Provider<g> loggerProvider) {
        return new VungleAdActivity_MembersInjector(eventBusProvider, uiExecutorProvider, sdkStateProvider, adMediatorProvider, audioHelperProvider, adPresenterMediatorProvider, adConfigFactoryProvider, loggerProvider);
    }

    public void injectMembers(VungleAdActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.c = (qg) this.f9744b.get();
        instance.d = (bz) this.f9745c.get();
        instance.e = (r) this.f9746d.get();
        instance.f = (b) this.f9747e.get();
        instance.g = (lm) this.f9748f.get();
        instance.h = (a) this.f9749g.get();
        instance.i = (u) this.f9750h.get();
        instance.j = (g) this.f9751i.get();
    }

    public static void injectEventBus(VungleAdActivity instance, Provider<qg> eventBusProvider) {
        instance.c = (qg) eventBusProvider.get();
    }

    public static void injectUiExecutor(VungleAdActivity instance, Provider<bz> uiExecutorProvider) {
        instance.d = (bz) uiExecutorProvider.get();
    }

    public static void injectSdkState(VungleAdActivity instance, Provider<r> sdkStateProvider) {
        instance.e = (r) sdkStateProvider.get();
    }

    public static void injectAdMediator(VungleAdActivity instance, Provider<b> adMediatorProvider) {
        instance.f = (b) adMediatorProvider.get();
    }

    public static void injectAudioHelper(VungleAdActivity instance, Provider<lm> audioHelperProvider) {
        instance.g = (lm) audioHelperProvider.get();
    }

    public static void injectAdPresenterMediator(VungleAdActivity instance, Provider<a> adPresenterMediatorProvider) {
        instance.h = (a) adPresenterMediatorProvider.get();
    }

    public static void injectAdConfigFactory(VungleAdActivity instance, Provider<u> adConfigFactoryProvider) {
        instance.i = (u) adConfigFactoryProvider.get();
    }

    public static void injectLogger(VungleAdActivity instance, Provider<g> loggerProvider) {
        instance.j = (g) loggerProvider.get();
    }
}
