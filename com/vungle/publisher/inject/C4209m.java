package com.vungle.publisher.inject;

import android.content.Context;
import android.telephony.TelephonyManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4209m implements Factory<TelephonyManager> {
    static final /* synthetic */ boolean f10398a = (!C4209m.class.desiredAssertionStatus());
    private final a f10399b;
    private final Provider<Context> f10400c;

    public /* synthetic */ Object get() {
        return m13396a();
    }

    public C4209m(a aVar, Provider<Context> provider) {
        if (f10398a || aVar != null) {
            this.f10399b = aVar;
            if (f10398a || provider != null) {
                this.f10400c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public TelephonyManager m13396a() {
        return (TelephonyManager) Preconditions.checkNotNull(this.f10399b.f((Context) this.f10400c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<TelephonyManager> m13395a(a aVar, Provider<Context> provider) {
        return new C4209m(aVar, provider);
    }
}
