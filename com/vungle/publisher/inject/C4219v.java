package com.vungle.publisher.inject;

import android.content.Context;
import android.net.wifi.WifiManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4219v implements Factory<WifiManager> {
    static final /* synthetic */ boolean f10471a = (!C4219v.class.desiredAssertionStatus());
    private final t f10472b;
    private final Provider<Context> f10473c;

    public /* synthetic */ Object get() {
        return m13430a();
    }

    public C4219v(t tVar, Provider<Context> provider) {
        if (f10471a || tVar != null) {
            this.f10472b = tVar;
            if (f10471a || provider != null) {
                this.f10473c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public WifiManager m13430a() {
        return (WifiManager) Preconditions.checkNotNull(this.f10472b.a((Context) this.f10473c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<WifiManager> m13429a(t tVar, Provider<Context> provider) {
        return new C4219v(tVar, provider);
    }
}
