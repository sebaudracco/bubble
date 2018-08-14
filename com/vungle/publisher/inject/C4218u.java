package com.vungle.publisher.inject;

import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import com.vungle.publisher.env.a;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4218u implements Factory<DeviceIdStrategy> {
    static final /* synthetic */ boolean f10468a = (!C4218u.class.desiredAssertionStatus());
    private final t f10469b;
    private final Provider<a> f10470c;

    public /* synthetic */ Object get() {
        return m13428a();
    }

    public C4218u(t tVar, Provider<a> provider) {
        if (f10468a || tVar != null) {
            this.f10469b = tVar;
            if (f10468a || provider != null) {
                this.f10470c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public DeviceIdStrategy m13428a() {
        return (DeviceIdStrategy) Preconditions.checkNotNull(this.f10469b.a((a) this.f10470c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<DeviceIdStrategy> m13427a(t tVar, Provider<a> provider) {
        return new C4218u(tVar, provider);
    }
}
