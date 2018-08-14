package com.vungle.publisher.inject;

import android.content.Context;
import android.net.ConnectivityManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4201e implements Factory<ConnectivityManager> {
    static final /* synthetic */ boolean f10375a = (!C4201e.class.desiredAssertionStatus());
    private final a f10376b;
    private final Provider<Context> f10377c;

    public /* synthetic */ Object get() {
        return m13380a();
    }

    public C4201e(a aVar, Provider<Context> provider) {
        if (f10375a || aVar != null) {
            this.f10376b = aVar;
            if (f10375a || provider != null) {
                this.f10377c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ConnectivityManager m13380a() {
        return (ConnectivityManager) Preconditions.checkNotNull(this.f10376b.d((Context) this.f10377c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ConnectivityManager> m13379a(a aVar, Provider<Context> provider) {
        return new C4201e(aVar, provider);
    }
}
